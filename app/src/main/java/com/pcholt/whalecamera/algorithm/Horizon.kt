package com.pcholt.whalecamera.algorithm

import kotlin.math.*

/**
 * Manage all trigonometric calculations for calculating distances given the height and angle below the horizon, or calculating
 * the angle below the horizon given a distance.
 */

const val EARTH_RADIUS = 6370000 // in metres
class Horizon(private val height: Double, private val radius: Double = EARTH_RADIUS.toDouble()) {

    fun distanceGivenTheta(theta: Double) : Double? { // metres
        val b = asin((height+radius) * sin(theta) / radius)
        return if (b.isNaN())
             null
        else
            (b - theta) * radius
    }

    fun thetaGivenHorizon(): Double {
        val a = acos(radius/(radius+height))
        return PI/2.0 - a
    }

    fun distanceToHorizon(): Double {
        val a = acos(radius/(radius+height))
        return a * radius
    }

    fun thetaGivenDistance(distance: Double) : Double {
        val a = sqrt(sqr(radius) - 2*radius*(height + radius)*cos(distance/radius) + sqr(height + radius))
        return asin(radius * sin(distance/radius) / a)
    }

    private fun sqr(x: Double) = x * x
}