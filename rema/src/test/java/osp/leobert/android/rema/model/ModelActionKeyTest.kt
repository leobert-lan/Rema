package osp.leobert.android.rema.model

import com.google.common.testing.EqualsTester
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
        keyM1 = ModelActionKey(RemaTest.M1().apply { this.str = "str" }, RemaTest.ActionM1R)
        keyM1_2 = ModelActionKey(RemaTest.M1().apply { this.str = "str" }, RemaTest.ActionM1R)
    }

    @Test
    fun testEquals() {
//        assertEquals(keyM1, keyM1_2)

        EqualsTester().addEqualityGroup(
            keyM1,
            keyM1,//幂等性
            keyM1_2
        ).addEqualityGroup(
            ModelActionKey(RemaTest.M1().apply { this.str = "str2" }, RemaTest.ActionM1R)
        ).addEqualityGroup(
            ModelActionKey(RemaTest.M1(), RemaTest.ActionM1R)
        ).addEqualityGroup(
            ModelActionKey(RemaTest.M(), RemaTest.ActionMR),
            ModelActionKey(RemaTest.M(), RemaTest.ActionMR)
        ).testEquals()
    }

    @Test
    fun testHashCode() {
        assertEquals(keyM1.hashCode(), keyM1_2.hashCode())
    }
}