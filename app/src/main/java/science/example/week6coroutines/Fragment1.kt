package science.example.week6coroutines

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.*
import science.example.week6coroutines.databinding.Fragment1Binding
import java.math.BigDecimal


class Fragment1 : Fragment() {

    lateinit var binding : Fragment1Binding
    private var answer: String = ""
    private var counter: Double = 0.0
    private var number: Int = 0
    private var y : BigDecimal = BigDecimal(4)
    private var x: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragment1Binding.inflate(inflater)
        val job: Job = GlobalScope.launch(Dispatchers.IO) {
            Pi()
        }
        return binding.root
    }


    private suspend fun Pi() = coroutineScope {
        launch {
            while (true) {
                counter++
                if (number % 2 == 0) {
                    y -= (BigDecimal(4).divide(BigDecimal(x + 2), 300, 0))
                } else {
                    y += (BigDecimal(4).divide(BigDecimal(x + 2), 300, 0))
                }
                answer = y.toString()
                x += 2
                if (counter % 1000 == 0.0) {
                    binding.textPi.text = answer
                }
                number++
            }
        }.join()
    }


    companion object {
        @JvmStatic
        fun newInstance() = Fragment1()
    }
}

