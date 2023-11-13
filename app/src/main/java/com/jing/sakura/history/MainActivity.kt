package com.jing.sakura.history

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.jing.sakura.R
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager


class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disableSSLCheck()
        setContentView(R.layout.acticity_main)
    }

    private fun disableSSLCheck() {
        HttpsURLConnection.setDefaultHostnameVerifier { _, _ -> true }
        val trustManagers = arrayOf(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun checkServerTrusted(
                chain: Array<out X509Certificate>?,
                authType: String?
            ) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()
        })
        SSLContext.getInstance("TLS").apply {
            init(null, trustManagers, SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(socketFactory)
        }
    }
}