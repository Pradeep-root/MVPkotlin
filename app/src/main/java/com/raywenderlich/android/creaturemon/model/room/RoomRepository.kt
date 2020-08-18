
package com.raywenderlich.android.creaturemon.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.raywenderlich.android.creaturemon.app.CreaturemonApplication
import com.raywenderlich.android.creaturemon.model.Creature
import com.raywenderlich.android.creaturemon.model.CreatureRepository

class RoomRepository : CreatureRepository {
  private val creatureDao: CreatureDao = CreaturemonApplication.database.creatureDao()

  private val allCreatures: LiveData<List<Creature>>

  init {
      allCreatures = creatureDao.getAllCreatures()
  }


  private class InsertAsyncTask internal constructor(private val dao: CreatureDao) : AsyncTask<Creature, Void, Void>() {
    override fun doInBackground(vararg params: Creature): Void? {
      dao.insert(creature = params[0])
      return null
    }
  }

  private class DeleteAsyncTask internal constructor(private val dao: CreatureDao) : AsyncTask<Creature, Void, Void>() {
    override fun doInBackground(vararg params: Creature): Void? {
      dao.clearAllCreature(*params)
      return null
    }
  }

  override fun saveCreature(creature: Creature) {
    InsertAsyncTask(creatureDao).execute(creature)
  }

  override fun getAllCreatures(): LiveData<List<Creature>> {
    return allCreatures
  }

  override fun clearAllCreatures() {
    val creatureArray = allCreatures.value?.toTypedArray()
    if(creatureArray!= null){
      DeleteAsyncTask(creatureDao).execute(*creatureArray)
    }
  }
}