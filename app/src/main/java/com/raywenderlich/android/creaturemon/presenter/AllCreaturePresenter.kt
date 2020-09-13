package com.raywenderlich.android.creaturemon.presenter

import androidx.lifecycle.LiveData
import com.raywenderlich.android.creaturemon.model.Creature
import com.raywenderlich.android.creaturemon.model.CreatureRepository
import com.raywenderlich.android.creaturemon.model.room.RoomRepository

class AllCreaturePresenter(private val repository: CreatureRepository = RoomRepository()) :
        BasePresenter<AllCreatureContract.View>(), AllCreatureContract.Presenter{


    override fun getAllCreatures(): LiveData<List<Creature>> {
       return repository.getAllCreatures()
    }

    override fun clearAllCreatures() {
        repository.clearAllCreatures()
        getView()?.showCreaturesCleared()
    }

}