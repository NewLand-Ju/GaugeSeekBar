package com.tenclouds.gaugeprogress

import android.graphics.*

class ThumbDrawable(centerPosition: PointF,
                    thumbColor: Int,
                    var progress: Float,
                    private val startAngle: Float,
                    private val thumbRadius: Float) : DrawableEntity(centerPosition) {


    companion object {
        private const val DEGREE_TO_RADIAN_RATIO = 0.0174533
    }

    private val whitePaint = Paint().apply {
        color = Color.WHITE
        alpha = 255
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val thumbOuterPaint = Paint().apply {
        isAntiAlias = true
        color = thumbColor
        alpha = 102
    }

    private val thumbInnerPaint = Paint().apply {
        isAntiAlias = true
        color = thumbColor
    }

    fun draw(canvas: Canvas, progress: Float) {
        this.progress = progress
        draw(canvas)
    }

    override fun draw(canvas: Canvas) {
        val radius = Math.min(centerPosition.x, centerPosition.y) - thumbRadius

        val angle = (startAngle + (360 - 2 * startAngle) * progress) * DEGREE_TO_RADIAN_RATIO

        val indicatorX = centerPosition.x - Math.sin(angle) * radius
        val indicatorY = Math.cos(angle) * radius + centerPosition.y

        canvas.apply {
            drawCircle(indicatorX.toFloat(), indicatorY.toFloat(), thumbRadius, thumbOuterPaint)
            drawCircle(indicatorX.toFloat(), indicatorY.toFloat(), thumbRadius / 2f, thumbInnerPaint)
            drawCircle(indicatorX.toFloat(), indicatorY.toFloat(), 3f, whitePaint)
        }
    }

    override fun setAlpha(alpha: Int) {}

    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT

    override fun setColorFilter(colorFilter: ColorFilter?) {}


}