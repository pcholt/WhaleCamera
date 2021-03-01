package com.pcholt.whalecamera.algorithm

import org.junit.Test

import org.junit.Assert.*
import java.lang.Exception
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.atan

class HorizonTest {

    @Test
    fun testDistanceToHorizon() {
        assertEquals(
            "tiny world has close horizon",
            acos(.5),
            Horizon(1.0, 1.0).distanceToHorizon(),
            0.01
        )
        assertEquals(
            "larger world has far horizon",
            acos(10 / 11.0) * 10.0,
            Horizon(1.0, 10.0).distanceToHorizon(),
            0.01
        )
    }

    @Test
    fun distanceGivenDown() {
        assertEquals(
            "Straight down is distance 0",
            0.0,
            Horizon(1.0, 100000.0).distanceGivenTheta(0.0) ?: throw Exception(),
            0.001
        )
    }

    @Test
    fun distanceGivenHorizontal() {
        assertNull(
            "Horizontal never hits the planet, no matter how big",
            Horizon(1.0, 100000.0).distanceGivenTheta(PI/2.0)
        )
    }
    @Test
    fun distanceGivenTheta2() {
        assertEquals(
            "45 degrees where height==1 on a big planet should be about 1",
            1.0,
            Horizon(1.0, 100000.0).distanceGivenTheta(PI / 4.0) ?: throw Exception(),
            0.01
        )
    }

    @Test
    fun thetaGivenDistance() {
        assertEquals("distance 1 with height 1 should be about 45 degrees",
            PI/4.0, Horizon(1.0,100000.0).thetaGivenDistance(1.0), 0.001)
        assertEquals("distance 2 with height 1 should be about atan(2) degrees",
            atan(2.0), Horizon(1.0,100000.0).thetaGivenDistance(2.0), 0.001)
        assertEquals("distance 6 with height 1 should be about atan(6) degrees",
            atan(6.0), Horizon(1.0,100000.0).thetaGivenDistance(6.0), 0.0001)
        assertEquals("distance 10 with height 1 should be about atan(10) degrees",
            atan(10.0), Horizon(1.0,100000.0).thetaGivenDistance(10.0), 0.0001)
        assertTrue("distance 10 with height 1 should be less than atan(10) degrees",
            Horizon(1.0,100000.0).thetaGivenDistance(10.0) < atan(10.0)
        )
    }
}