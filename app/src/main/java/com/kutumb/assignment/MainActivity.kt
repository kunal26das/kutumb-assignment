package com.kutumb.assignment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.databinding.DataBindingUtil
import com.kutumb.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val timerActivity = registerForActivityResult(CountDownActivity.Contract()) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.start.setOnClickListener {
            val time = binding.editText.text?.toString()?.toIntOrNull()
            startCountDownService(time)
            timerActivity.launch(time)
            finish()
        }
    }

    private fun startCountDownService(time: Int?) {
        val intent = Intent(this, CountDownService::class.java)
        intent.putExtra(Constants.KEY_TIME, time)
        startService(intent)
    }

    class Contract : ActivityResultContract<Any?, Boolean>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            return Intent(context, MainActivity::class.java)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
            return resultCode == RESULT_OK
        }
    }

}