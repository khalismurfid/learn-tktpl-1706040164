package id.ac.ui.cs.mobileprogramming.helloworld

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.gson.jsonBody
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_wifi_list.*
import org.json.JSONObject

/**
 * A fragment representing a list of Items.
 */
class WifiFragment : Fragment() {

    private var columnCount = 1
    private lateinit var wifiManager: WifiManager
    private var wifiList : MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wifi_list, container, false)
        activity?.sendWifi?.setOnClickListener {
            sendToRequestBin()
        }
        wifiManager = activity?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (!checkIfAlreadyHavePermission()) {
            requestForSpecificPermission();
        } else{
            startWifiScanning()
        }
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyWifiRecyclerViewAdapter(listOf())
            }
        }
        return view
    }

    private fun checkIfAlreadyHavePermission(): Boolean {
        val result =
            activity?.applicationContext?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION) }
        return result == PackageManager.PERMISSION_GRANTED
    }
    private fun requestForSpecificPermission() {
        activity?.let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_WIFI_STATE
                ),
                101
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            101 -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startWifiScanning()
            } else {
                //not granted
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun startWifiScanning(){
        val wifiScanReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
                if (success) {
                    scanSuccess()
                } else {
                    scanFailure()
                }
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        activity?.applicationContext?.registerReceiver(wifiScanReceiver, intentFilter)

        val success = wifiManager.startScan()
        if (!success) {
            // scan failure handling
            scanFailure()
        }
    }

    private fun scanSuccess() {
        val results = wifiManager.scanResults
        wifiList = mutableListOf<String>()
        val adapterList =  mutableListOf<String>()
        for (res in results) {
            adapterList.add(res.SSID)
            wifiList!!.add(res.SSID)
        }
        list.layoutManager = LinearLayoutManager(activity?.applicationContext)
        val recyclerAdapter =  MyWifiRecyclerViewAdapter(adapterList)
        list.adapter = recyclerAdapter
        if(wifiList!!.isNotEmpty()){
            activity?.sendWifi?.isEnabled=true
        }
    }

    private fun sendToRequestBin(){
        val bodyJson = JSONObject("""{"wifi":${wifiList.toString()}}""")
        val httpAsync = "https://0290ef178f1bf33568189e45e0165eb8.m.pipedream.net/"
            .httpPost().jsonBody(bodyJson).response {
                request, response, result ->
            when(result.component2() ) {
                null -> {
                    val ex = result.get()
                }
                else -> {
                    Log.e("fail", "${response}")
                }
            }
        }
        httpAsync.join()
    }

    private fun scanFailure() {
        // handle failure: new scan did NOT succeed
        // consider using old scan results: these are the OLD results!
        val results = wifiManager.scanResults
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            WifiFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}