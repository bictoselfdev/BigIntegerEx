package com.example.bigintegerex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.bigintegerex.databinding.ActivityMainBinding
import java.math.BigInteger

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setListener()
    }

    private fun setListener() {

        binding.btnBinaryApply.setOnClickListener {

            val binaryString = binding.etBinary.text.toString()
            if (binaryString.isEmpty()) return@setOnClickListener

            binaryApply(binaryString)
        }

        binding.btnDecimalApply.setOnClickListener {

            val decimalString = binding.etDecimal.text.toString()
            if (decimalString.isEmpty()) return@setOnClickListener

            decimalApply(decimalString)
        }

        binding.btnHexDecimalApply.setOnClickListener {

            val hexString = binding.etHexDecimal.text.toString()
            if (hexString.isEmpty()) return@setOnClickListener

            hexDecimalApply(hexString)
        }

        binding.btnSetBit1.setOnClickListener {

            val indexString = binding.etBitIndex.text.toString()
            if (indexString.isEmpty()) return@setOnClickListener

            try {
                setBit(indexString.toInt(), true)
            } catch (e: Exception) {
                e.printStackTrace()
                binding.tvResult.text = "fail"
            }
        }

        binding.btnSetBit0.setOnClickListener {

            val indexString = binding.etBitIndex.text.toString()
            if (indexString.isEmpty()) return@setOnClickListener

            try {
                setBit(indexString.toInt(), false)
            } catch (e: Exception) {
                e.printStackTrace()
                binding.tvResult.text = "fail"
            }
        }

        binding.btnGetBit.setOnClickListener {

            val indexString = binding.etBitIndex.text.toString()
            if (indexString.isEmpty()) return@setOnClickListener

            try {
                getBit(indexString.toInt())
            } catch (e: Exception) {
                e.printStackTrace()
                binding.tvResult.text = "fail"
            }
        }
    }

    private fun binaryApply(binaryString: String) {
        val bigIntegerBinary = BigInteger(binaryString, 2)
        binding.etDecimal.setText(bigIntegerBinary.toString(10))
        binding.etHexDecimal.setText(bigIntegerBinary.toString(16))

        binding.tvBitCount.text = bigIntegerBinary.bitLength().toString()
    }

    private fun decimalApply(decimalString: String) {
        val bigIntegerDecimal = BigInteger(decimalString, 10)
        binding.etBinary.setText(bigIntegerDecimal.toString(2))
        binding.etHexDecimal.setText(bigIntegerDecimal.toString(16))

        binding.tvBitCount.text = bigIntegerDecimal.bitLength().toString()
    }

    private fun hexDecimalApply(hexString: String) {
        val bigIntegerHex = BigInteger(hexString, 16)
        binding.etBinary.setText(bigIntegerHex.toString(2))
        binding.etDecimal.setText(bigIntegerHex.toString(10))

        binding.tvBitCount.text = bigIntegerHex.bitLength().toString()
    }

    private fun setBit(index: Int, isSet: Boolean) {

        val binaryString = binding.etBinary.text.toString()
        if (binaryString.isEmpty()) return

        val bigInteger = if (isSet) {
            BigInteger(binaryString, 2).setBit(index) // set bit 1
        } else {
            BigInteger(binaryString, 2).clearBit(index) // set bit 0
        }

        binding.etBinary.setText(bigInteger.toString(2))
        binding.etDecimal.setText(bigInteger.toString(10))
        binding.etHexDecimal.setText(bigInteger.toString(16))

        binding.tvResult.text = "success"
    }

    private fun getBit(index: Int) {

        val binaryString = binding.etBinary.text.toString()
        if (binaryString.isEmpty()) return

        val isSet = BigInteger(binaryString, 2).testBit(index)
        binding.tvResult.text = if (isSet) "1" else "0"
    }
}