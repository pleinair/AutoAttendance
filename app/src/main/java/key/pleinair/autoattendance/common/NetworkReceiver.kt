package key.pleinair.autoattendance.common

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioManager
import android.net.NetworkInfo
import android.net.ConnectivityManager
import android.text.TextUtils
import android.util.Log
import android.widget.Toast




class NetworkReceiver : BroadcastReceiver() {
    // メンバ変数定義？
    companion object {
        const val TAG = "receiver"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null ) return
        if (intent == null) return

        // API28~ 非推奨になる可能性がある
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo

        Log.d(TAG, "ほげーーーーー")
        Log.d(TAG, context.toString())
        Log.d(TAG, networkInfo.toString())

        val toast = Toast.makeText(context, networkInfo.extraInfo, Toast.LENGTH_LONG)
        toast.view.setBackgroundColor(Color.GREEN)
        toast.show()
        // TODO 特定のSSIDの場合はAPIを叩く
        if (TextUtils.equals(networkInfo.extraInfo , "imuraya-g")) {
        }
    }

    fun changeAllVolume(context: Context?) {

        if (context == null) return
        val am = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val maxRingVolume = am.getStreamMaxVolume(AudioManager.STREAM_RING).toFloat()
        val maxMusicVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC).toFloat()
        val maxAlermVolume = am.getStreamMaxVolume(AudioManager.STREAM_ALARM).toFloat()

        val currentRingVolume = am.getStreamVolume(AudioManager.STREAM_RING).toFloat()
        val perVolume = (currentRingVolume / maxRingVolume)

        am.setStreamVolume(AudioManager.STREAM_MUSIC, (maxMusicVolume * perVolume).toInt(), 0)
        am.setStreamVolume(AudioManager.STREAM_ALARM, (maxAlermVolume * perVolume).toInt(), 0)

    }
}