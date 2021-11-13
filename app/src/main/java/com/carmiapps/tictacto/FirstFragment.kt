package com.carmiapps.tictacto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carmiapps.tictacto.databinding.FragmentFirstBinding
import com.carmiapps.tictacto.game.Game
import com.carmiapps.tictacto.game.GameView
import android.widget.TextView
import android.text.method.ScrollingMovementMethod
import android.R

import com.plattysoft.leonids.ParticleSystem







/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), GameView {

    private var _binding: FragmentFirstBinding? = null

    val game: Game = Game(this)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            textviewInstraction.movementMethod = ScrollingMovementMethod()

            buttonFirst.setOnClickListener  { game.onInput("1") }
            buttonSecond.setOnClickListener { game.onInput("2") }
            buttonThird.setOnClickListener  { game.onInput("3") }
            buttonForth.setOnClickListener  { game.onInput("4") }
        }

        game.newGame()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun addInstraction(instraction: String?) {
        activity?.runOnUiThread {
            val text =  binding.textviewInstraction.text.toString() + "\n" + instraction
            binding.textviewInstraction.text = text
            binding.textviewInstraction.bringPointIntoView(text.length)
        }
    }

    override fun setBoard(board: String?) {
        activity?.runOnUiThread {
            binding.textviewBoard.text = board
        }
    }

    override fun onGameEnd() {
        activity?.runOnUiThread {
            showFireworks()
            view?.postDelayed( {
                binding.apply {
                    textviewBoard.text = ""
                    textviewInstraction.text = ""
                }
                game.newGame()
            }, 10000)
        }
    }

    fun showFireworks(){
        ParticleSystem(requireActivity(), 80, R.drawable.btn_star, 10000)
            .setSpeedModuleAndAngleRange(0f, 0.3f, 0, 360)
            .setRotationSpeed(150f)
            .setAcceleration(0.00005f, 90)
            .emit(binding.textviewBoard, 8, 10000)

        ParticleSystem(requireActivity(), 80, R.drawable.btn_star, 10000)
            .setSpeedModuleAndAngleRange(0f, 0.3f, 0, 360)
            .setRotationSpeed(150f)
            .setAcceleration(0.00005f, 90)
            .emit(binding.textviewInstraction, 8, 10000)
    }
}