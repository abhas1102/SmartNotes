package com.example.smartnote.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.smartnote.R
import com.example.smartnote.databinding.AddNotesBinding
import com.example.smartnote.model.TodayNotes
import java.io.File

class AddNotesActivity : AppCompatActivity() {
    lateinit var binding:AddNotesBinding
    @RequiresApi(Build.VERSION_CODES.S)
    var file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"recordtest.3gp")

    lateinit var mediaRecorder: MediaRecorder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.add_notes)
        mediaRecorder = MediaRecorder()
        checkPermission()
        binding.audioRecorder.setOnClickListener {
            binding.stopRecording.visibility = View.VISIBLE
            binding.audioRecorder.visibility = View.INVISIBLE
            recordAudio()
        }
        binding.stopRecording.setOnClickListener {
            binding.stopRecording.visibility = View.GONE
            binding.playRecording.visibility = View.VISIBLE
            stopRecording()
        }
        binding.playRecording.setOnClickListener {
            playRecording()
        }
        submitNotes()
    }
    @RequiresApi(Build.VERSION_CODES.S)
    fun submitNotes() {
        binding.btnSubmitNote.setOnClickListener {
            val note = TodayNotes(binding.addTitle.text.toString(), binding.addDescription.text.toString(),file.path)
            val intent = Intent()
            intent.putExtra("note",note)
            setResult(RESULT_OK,intent)
            finish()
        }
    }

    fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.RECORD_AUDIO) !=
            PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECORD_AUDIO,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE),111)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            true
    }
    @RequiresApi(Build.VERSION_CODES.S)
    fun recordAudio() {
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
        mediaRecorder.setOutputFile(file)
        mediaRecorder.prepare()
        mediaRecorder.start()
    }
    fun stopRecording() {
        mediaRecorder.stop()
    }
    @RequiresApi(Build.VERSION_CODES.S)
    fun playRecording() {
        var mp = MediaPlayer()
        mp.setDataSource(file.absolutePath)
        mp.prepare()
        mp.start()
    }


}