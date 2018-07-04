package key.pleinair.autoattendance.common

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager

/**
 * Created by okajima on 16/04/11.
 */
class VolumeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null ) return
        if (intent == null) return

        if (intent.action.equals("android.media.VOLUME_CHANGED_ACTION")) {
            changeAllVolume(context)
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