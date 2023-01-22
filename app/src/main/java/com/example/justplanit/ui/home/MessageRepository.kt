package com.example.justplanit.ui.home

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MessageRepository {
    //list of random advice that is used in the event that the api call fails
    val messages: List<slip>

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
            slip(167,
                "No one knows anyone else in the way you do."),
            slip(134,
                "The person who never made a mistake never made anything."),
            slip(64,
                "You don't need to floss all of your teeth. Only the ones you want to keep."),
            slip(107,
                "If you don't ask, you don't get."),
            slip(59,
                "Don't be afraid of silly ideas."),
            slip(46,
                "Try going commando to an important meeting, NB: don't wear a skirt."),
            slip(28,
                "When you're looking up at birds flying overhead, keep your mouth closed."),
            slip(17,
                "Sometimes it's best to ignore other people's advice."),
            slip(81,
                "Age is of no importance, unless you are a cheese."),
            slip(182,
                "Most things look better when you put them in a circle."),
            slip(179,
                "Never regret. If it's good, it's wonderful. If it's bad, it's experience."),
            slip(69,
                "Visitors are like fish: As much as you might like them, after three days they start to smell."),
            slip(208,
                "Play is the true mother of invention."),
            slip(109,
                "To cleanly remove the seed from an Avocado, lay a knife firmly across it, and twist."),
            slip(91,
                "Drink a glass of water before meals."),
            slip(121,
                "If you think your headphones are dying, check the socket for fluff with a straightened paperclip."),
            slip(40,
                "Never run with scissors."),
            slip(144,
                "Pedantry is fine, unless you're on the receiving end. And not a pedant."),
            slip(23,
                "Your smile could make someone's day, don't forget to wear it."),
            slip(160,
                "Enjoy a little nonsense now and then."),
        )
    }

    fun messageList(success: (messageList: List<slip>) -> Unit, error: (errorMessage: String) -> Unit) {
        MessageApi.retrofitService.advice().enqueue(object: Callback<List<slip>> {
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