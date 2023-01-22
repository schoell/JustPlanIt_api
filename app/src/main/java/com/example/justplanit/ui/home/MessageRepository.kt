package com.example.justplanit.ui.home

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MessageRepository {
    private val messages: List<slip>

    init {
        messages = listOf(
            slip(129,
            "Stop procrastinating."),
            slip(10,
                "Never pay full price for a sofa at DFS."),
            slip(131,
                "YOLO"),
            slip(192,
                "Don't take it personally."),
            slip(82,
                "For every complex problem there is an answer that is clear, simple, and wrong."),
            slip(186,
                "One of the single best things about being an adult, is being able to buy as much LEGO as you want."),
            slip(39,
                "Never run a marathon in Crocs."),
            slip(70,
                "Don't try and bump start a motorcycle on an icy road."),
            slip(142,
                "If you don't like the opinion you've been given, get another one."),
            slip(204,
                "The best nights out are when people around you are simply having fun."),
        )
    }

    fun messageList(success: (lessonList: List<slip>) -> Unit, error: (errorMessage: String) -> Unit) {
        MessageApi.retrofitService.lessons().enqueue(object: Callback<List<slip>> {
            override fun onFailure(call: Call<List<slip>>, t: Throwable) {
                error("The call failed")
            }

            override fun onResponse(call: Call<List<slip>>, response: Response<List<slip>>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    success(responseBody)
                } else {
                    error("Something went wrong")
                }
            }

        })
    }
}