package com.example.roll_the_dice

import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private var soundPool: SoundPool? = null
    private var soundId1: Int = 0
    private var soundId2: Int = 0
    private var soundId3: Int = 0
    private var soundId4: Int = 0
    private var soundId5: Int = 0
    private var soundId6: Int = 0
    var play_sound_bufffer = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ImageView>(R.id.dice).setOnClickListener {
            MainScope().launch {

                var Random_Number = Random.nextInt(1..6)
                var rotateDirection = arrayListOf(-1, 1).random()
                var random_Buffer_XY = Random.nextBoolean()
                var rotation_Time: Long = 1
                var numberOfRotations_begin = Random.nextInt(1..2)
                var numberOfRotations_end = Random.nextInt(5..7)
                var Dice_scale: Float = 0.05F


                for (i in numberOfRotations_begin..numberOfRotations_end) {

                    Random_Number = Random.nextInt(1..6)
                    rotateDirection = arrayListOf(-1, 1).random()
                    random_Buffer_XY = Random.nextBoolean()


                    if (random_Buffer_XY) {
                        for (i in 0..89) {
                            it.rotationX = it.rotationX + rotateDirection
                            it.scaleX = it.scaleX + Dice_scale
                            it.scaleY = it.scaleY + Dice_scale
                            delay(rotation_Time)}}
                        else {
                            for (i in 0..89) {
                                it.rotationY = it.rotationY + rotateDirection
                                it.scaleX = it.scaleX + Dice_scale
                                it.scaleY = it.scaleY + Dice_scale
                                delay(rotation_Time)}}



                    findViewById<ImageView>(R.id.dice).setImageResource(
                        when (Random_Number) {
                            1 -> R.drawable.one
                            2 -> R.drawable.two
                            3 -> R.drawable.three
                            4 -> R.drawable.four
                            5 -> R.drawable.five
                            6 -> R.drawable.six
                            else -> R.drawable.ic_launcher_foreground
                        }
                    )
                    if (random_Buffer_XY) {
                        for (i in 90..179) {
                            it.rotationX = it.rotationX + rotateDirection
                            it.scaleX = it.scaleX + Dice_scale
                            it.scaleY = it.scaleY + Dice_scale
                            delay(rotation_Time)
                        }

                    } else {
                        for (i in 90..179) {
                            it.rotationY = it.rotationY + rotateDirection
                            it.scaleX = it.scaleX + Dice_scale
                            it.scaleY = it.scaleY + Dice_scale
                            delay(rotation_Time)
                        }
                    }
                }
                play_sound_bufffer = Random_Number
                playSound(Random_Number)
            }
        }
        soundPool = SoundPool(1, AudioManager.STREAM_MUSIC, 0)
        soundId1 = soundPool!!.load(baseContext, R.raw.one, 1)
        soundId2 = soundPool!!.load(baseContext, R.raw.two, 1)
        soundId3 = soundPool!!.load(baseContext, R.raw.three, 1)
        soundId4 = soundPool!!.load(baseContext, R.raw.four, 1)
        soundId5 = soundPool!!.load(baseContext, R.raw.five, 1)
        soundId6 = soundPool!!.load(baseContext, R.raw.six, 1)


    }
    fun playSound(sound: Int) {
        var soundId = 0
        when (play_sound_bufffer) {
            1 -> soundId = soundId1
            2 -> soundId = soundId2
            3 -> soundId = soundId3
            4 -> soundId = soundId4
            5 -> soundId = soundId5
            6 -> soundId = soundId6
            else -> print("Error!")
        }
        soundPool?.play(soundId, 1F, 1F, 0, 0, 1F)
    }
}