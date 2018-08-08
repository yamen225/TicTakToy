package com.example.yamen.firstapp

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun buClick(view: View){
        val buSelected = view as Button
        var cellID = 0
        when(buSelected.id){
            R.id.bu1 -> cellID = 1
            R.id.bu2 -> cellID = 2
            R.id.bu3 -> cellID = 3
            R.id.bu4 -> cellID = 4
            R.id.bu5 -> cellID = 5
            R.id.bu6 -> cellID = 6
            R.id.bu7 -> cellID = 7
            R.id.bu8 -> cellID = 8
            R.id.bu9 -> cellID = 9
        }

        playGame(cellID, buSelected)
        val winner = find_winner()
        when(winner){
            -1 -> Toast.makeText(this, "Kosomoko both.",
                Toast.LENGTH_SHORT).show()
            1  -> Toast.makeText(this, "Kosom The Machine.",
                Toast.LENGTH_SHORT).show()
            2  -> Toast.makeText(this, "Kosomak.",
                    Toast.LENGTH_SHORT).show()
        }
    }


    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var active_player = 1

    fun playGame(cellID:Int, buSelected:Button){
        buSelected.isEnabled = false

        if (active_player == 1){
            buSelected.setBackgroundColor(Color.BLACK)
            buSelected.setTextColor(Color.WHITE)
            buSelected.text = "X"
            player1.add(cellID)
            active_player = 2
            auto_play()
        }else {
            buSelected.setBackgroundColor(Color.GRAY)
            buSelected.setTextColor(Color.BLACK)
            buSelected.text = "O"
            player2.add(cellID)
            active_player = 1
        }
    }

    fun find_winner():Int {
        var winner:Int = 0
        for (i in 1..3) {
            if (player1.contains(i) && player1.contains(i + 1) && player1.contains(i + 2)) {
                winner = 1
                return winner
            }
            if (player2.contains(i) && player2.contains(i + 1) && player2.contains(i + 2)) {
                winner = 2
                return winner
            }
        }
        for (i in 1..3) {
            if (player1.contains(0 * 3 + i) && player1.contains(1 * 3 + i) && player1.contains(i + 3 * 2)) {
                winner = 1
                return winner
            }
            if (player2.contains(i + 0 * 3) && player2.contains(i + 1 * 3) && player2.contains(i + 2 * 3)) {
                winner = 2
                return winner
            }

        }
        return winner
    }

    fun auto_play(){
        var emptyCells = ArrayList<Int>()
        for (cellID in 1..9){
            if (!player1.contains(cellID) && !player2.contains(cellID)) {
                emptyCells.add(cellID)
            }
        }
        val r = Random()
        val randIndex = r.nextInt(emptyCells.size-0)+0
        val cellID = emptyCells[randIndex]
        var buSelect:Button? = bu1
        when(cellID){
            1 -> buSelect = bu1
            2 -> buSelect = bu2
            3 -> buSelect = bu3
            4 -> buSelect = bu4
            5 -> buSelect = bu5
            6 -> buSelect = bu6
            7 -> buSelect = bu7
            8 -> buSelect = bu8
            9 -> buSelect = bu9
        }
        playGame(cellID, buSelected = buSelect!!)
    }
}
