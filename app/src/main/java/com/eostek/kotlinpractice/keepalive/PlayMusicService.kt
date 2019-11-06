package com.eostek.kotlinpractice.keepalive

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import com.eostek.kotlinpractice.R

class PlayMusicService:Service() {
    private val TAG = PlayMusicService::class.java.simpleName
    private var mMediaPlayer: MediaPlayer? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "$TAG---->onCreate,启动服务")
        mMediaPlayer = MediaPlayer.create(applicationContext, R.raw.silence) as MediaPlayer
        mMediaPlayer!!.isLooping = true
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Thread(Runnable { startPlayMusic() }).start()
        return Service.START_STICKY
    }

    private fun startPlayMusic() {
        if (mMediaPlayer != null) {
            Log.d(TAG, "启动后台播放音乐")
            mMediaPlayer!!.start()
        }
    }

    private fun stopPlayMusic() {
        if (mMediaPlayer != null) {
            Log.d(TAG, "关闭后台播放音乐")
            mMediaPlayer!!.stop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopPlayMusic()
        Log.d(TAG, "$TAG---->onCreate,停止服务")
        // 重启自己
        val intent = Intent(applicationContext, PlayMusicService::class.java)
        startService(intent)
    }
}