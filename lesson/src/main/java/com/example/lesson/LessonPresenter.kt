package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.Utils
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class LessonPresenter {

    companion object{

        const val  LESSON_PATH = "lessons"
    }

    private val activity: LessonActivity


    constructor(activity: LessonActivity) {
        this.activity = activity
    }

    private var lessons: List<Lesson> = ArrayList()

    private val type = object : TypeToken<List<Lesson>>() {

    }.type

    fun fetchData() {
        HttpClient.get(LESSON_PATH, type, object : EntityCallback<List<Lesson>> {
            override fun onSuccess(lessons: List<Lesson>) {
                this@LessonPresenter.lessons = lessons
                activity.runOnUiThread(Runnable { activity.showResult(lessons) })
            }

            override fun onFailure(message: String?) {
                activity.runOnUiThread(Runnable { Utils.toast(message!!) })
            }
        })
    }

    fun showPlayback() {
        val playbackLessons= ArrayList<Lesson>()
        for (lesson in lessons) {
            if (lesson.getState() === Lesson.State.PLAYBACK) {
                playbackLessons.add(lesson)
            }
        }
        activity.showResult(playbackLessons)
    }
}