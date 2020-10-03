package id.ac.ui.cs.mobileprogramming.helloworld

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Thread.sleep
import java.util.*


class QuizActivity : AppCompatActivity() {

    private lateinit var listQuestions : List<Question>
    private var questionNumber = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        listQuestions = generateQuiz()
        val startQuizButton : Button = findViewById<Button>(R.id.buttonStartQuiz)

        startQuizButton.setOnClickListener{
            handleQuestion()
            initTimer()
        }
    }

    private fun initTimer(){
        val startQuizButton : Button = findViewById<Button>(R.id.buttonStartQuiz)
        val timerTextView : TextView = findViewById<TextView>(R.id.timer)
        var seconds = 15

        startQuizButton.isEnabled = false
        timerTextView.text = "${seconds.toString()} Detik"

        val runnableTick = Runnable {
            timerTextView.text = "${seconds.toString()} Detik"
            if(seconds <= 5){
                timerTextView.setTextColor(Color.parseColor("#FF0000"))
            }
        }
        val runnableFinish = Runnable {
            timerTextView.text = "Waktu Habis!!"
            startQuizButton.text = "Soal Selanjutnya"
            startQuizButton.isEnabled = true
            checkAnswer()
        }
        Thread(Runnable {
            while(seconds > 0){
                sleep(1000)
                seconds -= 1
                timerTextView.post(runnableTick)
            }
            timerTextView.post(runnableFinish)
        }).start()
    }

    private fun checkAnswer(){
        val answerEditText : EditText = findViewById<EditText>(R.id.answer)
        val currentQuestion = listQuestions.get(questionNumber-1)
        if (currentQuestion.checkQuestion(answerEditText.text.toString().trim())){
            Toast.makeText(this@QuizActivity, "Jawaban Benar !! ", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this@QuizActivity, "Jawaban Salah !!  Yang benar adalah ${currentQuestion.answer}", Toast.LENGTH_LONG).show()
        }
    }

    private fun generateQuiz() : List<Question>{
        val questions = listOf(
            Question(question = "Bintang paling terang yang dapat dilihat dari bumi adalah ... ", answer = "Sirius" ),
            Question(question = "'How You Like That' merupakan lagu dari girlband ... ", answer = "Blackpink" ),
            Question(question = "Tokoh utama dalam anime naruto adalah ...", answer = "Naruto" )
        )
        return questions.shuffled()
    }

    private fun handleQuestion(){
        if(questionNumber< listQuestions.size){
            setNextQuestion()
            questionNumber ++
        } else {
            finishQuiz()
        }
    }

    private fun setNextQuestion(){
        val quizTextView : TextView = findViewById<TextView>(R.id.quiz_text)
        val answerEditText : EditText = findViewById<EditText>(R.id.answer)
        answerEditText.text.clear()
        quizTextView.text = listQuestions[questionNumber].question
    }

    private fun finishQuiz(){
        val quotesView : TextView = findViewById<TextView>(R.id.quiz_text)
        quotesView.text = "Selamat anda sudah menyelesaikan quiz!!"
    }



}

data class Question(val question: String, val answer:String) {
    fun checkQuestion(answer: String) : Boolean{
        return answer.toLowerCase(Locale.ROOT) == this.answer.toLowerCase(Locale.ROOT)
    }
}