package science.example.week6coroutines

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import science.example.week6coroutines.databinding.Fragment2Binding
import kotlin.random.Random


class Fragment2 : Fragment() {

    lateinit var binding : Fragment2Binding
    private var a: Int = 1
    private var count: Int = 0
    private var time: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment2Binding.inflate(inflater)
        binding.chronometer.start()

        //кнопка play, при нажатии меняем время и запускаем chronometer заново
        binding.buttonPlay.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime() + time
            binding.chronometer.start()
            a = 1
            count = 0
        }
        //кнопка stop, при нажатии останавливаем chronometer
        binding.buttonStop.setOnClickListener {
            time = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            a = 1
            count = 0
        }
        //кнопка replay, при нажатии перезапускаем chronometer
        binding.buttonReplay.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime()
            a = 1
            count = 0
            time = 0
        }
        binding.chronometer.setOnChronometerTickListener {
            val second: Long = (SystemClock.elapsedRealtime() - binding.chronometer.base)
            if (second / a in 20001..20999) {
                a++
                if (count == 0) {
                    count = 1
                    val randomColor = -Random.nextInt(255 * 255 * 255)
                    binding.fragment2.setBackgroundColor(randomColor)
                } else {
                    count = 0
                    val randomColor = -Random.nextInt(255 * 255 * 255)
                    binding.fragment2.setBackgroundColor(randomColor)
                }
            }
        }
        return binding.root
    }
    companion object {
        @JvmStatic
        fun newInstance() = Fragment2()
    }
}