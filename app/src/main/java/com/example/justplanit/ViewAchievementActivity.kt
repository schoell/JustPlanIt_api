package com.example.justplanit

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.get
import com.example.justplanit.R
import com.example.justplanit.ui.achievements.AchievementsFragment
import org.w3c.dom.Text

class ViewAchievementActivity : AppCompatActivity() {

    companion object {
        private const val CAMERA_PERMISSION_CODE = 1
        private const val CAMERA_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_achievement)


        val achievementName = intent.getStringExtra(AchievementsFragment.ACHIEVEMENT_ID)
        if (achievementName == null) {
            //finish()
        }

        val achievement: Achievement = SqlDatabase.getDatabase(this).getSqlData.selAchievementName(achievementName.toString())

        findViewById<TextView>(R.id.achievement_header).text = achievementName
        findViewById<ImageView>(R.id.achievement_image).setImageBitmap(Converter().bytesToBitmap(achievement.bild))
        findViewById<TextView>(R.id.achievement_note).text = achievement.kommentar
        findViewById<TextView>(R.id.achievement_date).text = "Achieved on " + Converter().dateToString(achievement.datum)


        findViewById<Button>(R.id.achievement_image_change).setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_CODE
                )
            }
        }





        findViewById<Button>(R.id.achievement_save).setOnClickListener {
            val image:Bitmap = findViewById<ImageView>(R.id.achievement_image).drawable.toBitmap()
            val imageBytes = Converter().bitmapToBytes(image)
            SqlDatabase.getDatabase(this).getSqlData.updAchievementImg(achievement.name,imageBytes)

            val note = findViewById<TextView>(R.id.achievement_note).text.toString()
            SqlDatabase.getDatabase(this).getSqlData.updAchievementNote(achievement.name,note)


            finish()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            } else {
                Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST_CODE) {
            val picture : Bitmap = data!!.extras!!.get("data") as Bitmap
            findViewById<ImageView>(R.id.achievement_image).setImageBitmap(picture)

        }
    }
}