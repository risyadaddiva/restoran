package com.example.pizzarest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast

class order_detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        val userId: String = intent.getStringExtra("USER_ID") ?: "User"
        val storeLocation = intent.getStringExtra("STORE_LOCATION")
        val order: String = intent.getStringExtra("FOOD_NAME") ?: "Item"

        val userTextView = findViewById<TextView>(R.id.user_id)
        userTextView.text = "$userId"

        val storeLocationTextView = findViewById<TextView>(R.id.location_textView)
        storeLocationTextView.text = "Store : $storeLocation"

        val orderTextView = findViewById<TextView>(R.id.order_textView)
        orderTextView.text = "$order sudah dipesan"


        val checkBoxAmbilSendiri = findViewById<CheckBox>(R.id.checkbox_ambil_sendiri)
        val checkBoxFastDelivery = findViewById<CheckBox>(R.id.checkbox_fast_delivery)
        val doneButton = findViewById<Button>(R.id.done_id_button)

        doneButton.setOnClickListener {
            if (checkBoxAmbilSendiri.isChecked && checkBoxFastDelivery.isChecked) {
                showToast("Mohon Maaf Pilih Satu Opsi untuk pengiriman.")
            } else if (!checkBoxAmbilSendiri.isChecked && !checkBoxFastDelivery.isChecked) {
                showToast("Mohon Maaf, Pilih Satu Opsi untuk pengiriman.")
            } else {
                val toastMessage = buildToastMessage(userId, order, checkBoxAmbilSendiri.isChecked, checkBoxFastDelivery.isChecked)
                showToast(toastMessage)
            }
        }

    }

    private fun buildToastMessage(userId: String, foodItem: String, ambilSendiriChecked: Boolean, fastDeliveryChecked: Boolean): String {
        val stringBuilder = StringBuilder("Terima kasih $userId sudah memesan\nditoko kami. Pesanan $foodItem ")

        if (ambilSendiriChecked) {
            stringBuilder.append("akan anda ambil sendiri.\n")
        }

        if (fastDeliveryChecked) {
            stringBuilder.append(". Pesanan anda akan dikirim menggunakan Fast Delivery.\n")
        }

        return stringBuilder.toString()
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}