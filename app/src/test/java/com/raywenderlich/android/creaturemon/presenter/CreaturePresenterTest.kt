package com.raywenderlich.android.creaturemon.presenter

import com.raywenderlich.android.creaturemon.model.AttributeType
import com.raywenderlich.android.creaturemon.model.Creature
import com.raywenderlich.android.creaturemon.model.CreatureAttributes
import com.raywenderlich.android.creaturemon.model.CreatureGenerator
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.concurrent.timer

class CreaturePresenterTest{

    private lateinit var creaturePresenter: CreaturePresenter

    @Mock
    lateinit var view : CreatureContract.View

    @Mock
    lateinit var mockGenerator : CreatureGenerator

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        creaturePresenter = CreaturePresenter(mockGenerator)
        creaturePresenter.setView(view)
    }

    @Test
    fun testIntelligenceSelected(){
        val attributes = CreatureAttributes(10,0,0)
        val stubCreature = Creature(attributes, 50)
        `when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)

        creaturePresenter.attributeSelected(AttributeType.INTELLIGENCE,3)

        verify(view, times(1)).showHitPoints("50")
    }

    @Test
    fun testStrengthSelected(){
        val attributes = CreatureAttributes(0, 10,0)
        val stubCreature = Creature(attributes, 30)
        `when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)

        creaturePresenter.attributeSelected(AttributeType.STRENGTH, 3)

        verify(view, times(1)).showHitPoints("30")

    }

    @Test
    fun testEnduranceSelected(){
        val attributes = CreatureAttributes(0,0,10)
        val stubCreature = Creature(attributes, 40)
        `when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)

        creaturePresenter.attributeSelected(AttributeType.ENDURANCE, 3)

        verify(view, times(1)).showHitPoints("40")
    }

}