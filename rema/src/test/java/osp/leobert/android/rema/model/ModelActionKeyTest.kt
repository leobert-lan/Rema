package osp.leobert.android.rema.model

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import osp.leobert.android.rema.RemaTest

/**
 *
 * **Package:** osp.leobert.android.rema.model
 *
 * **Classname:** ModelActionKeyTest
 * Created by leobert on 2021/5/21.
 */
class ModelActionKeyTest {

    private lateinit var keyM1: ModelActionKey<RemaTest.M1, RemaTest.R>
    private lateinit var keyM1_2: ModelActionKey<RemaTest.M1, RemaTest.R>

    @Before
    fun setUp() {
        keyM1 = ModelActionKey(RemaTest.M1(), RemaTest.ActionM1R)
        keyM1_2 = ModelActionKey(RemaTest.M1(), RemaTest.ActionM1R)


    }

    @Test
    fun testEquals() {
        assertEquals(keyM1, keyM1_2)
    }

    @Test
    fun testHashCode() {
        assertEquals(keyM1.hashCode(), keyM1_2.hashCode())
    }
}