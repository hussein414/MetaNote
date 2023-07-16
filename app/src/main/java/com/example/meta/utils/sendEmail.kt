package com.example.meta.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress

import javax.mail.internet.MimeMessage


fun sendEmailData(recipient: String, subject: String, message: String, activity: Activity) {
    val intent = Intent(Intent.ACTION_SEND)

    intent.data = Uri.parse("mailto:")
    intent.type = "text/plain"

    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))

    intent.putExtra(Intent.EXTRA_SUBJECT, subject)

    intent.putExtra(Intent.EXTRA_TEXT, message)

    try {

        activity.startActivity(Intent.createChooser(intent, "Choose Email Client..."))
    } catch (e: Exception) {

        Toast.makeText(activity.applicationContext, e.message, Toast.LENGTH_LONG).show()
    }
}


fun sendEmailDataSMPT(recipient: String, subject: String, message: String) {
    val senderEmail = "ehossein54@gmail.com"
    val password = "sayehsiyah_th"
    val stringHost = "smtp.gmail.com"
    val properties = System.getProperties()
    properties["smtp.gmail.host"] = stringHost
    properties["mail.smtp.port"] = "465"
    properties["mail.smtp.ssl.enable"] = "true"
    properties["mail.smtp.auth"] = "true"
    val session = Session.getInstance(
        properties,
        object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(senderEmail, password)
            }
        }
    )


    val mimeMessage = MimeMessage(session)
    mimeMessage.addRecipient(Message.RecipientType.TO, InternetAddress(recipient))
    mimeMessage.subject = subject
    mimeMessage.setText(message)
    val thread = Thread {
        try {
            Transport.send(mimeMessage)
        } catch (e: MessagingException) {
            e.printStackTrace()
        }
    }
    thread.start()
}