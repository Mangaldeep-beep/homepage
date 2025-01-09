package com.example.homepage.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.withTransform
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

private data class LightParticle(
    val initialX: Float,
    val initialY: Float,
    var currentX: Float,
    var currentY: Float,
    val radius: Float,
    val speed: Float,
    val angle: Float,
    val color: Color,
    val baseAlpha: Float,
    val pulseSpeed: Float
)

private data class MutableBubbleState(
    var currentX: Float,
    var currentY: Float,
    var baseY: Float,
    var horizontalPhase: Float = Random.nextFloat() * 2 * PI.toFloat(),
    var scale: Float = 1f,
    var alpha: Float = 1f,
    var rotation: Float = 0f
)

private data class FloatingBubble(
    var state: MutableBubbleState,
    val size: Float,
    val horizontalSpeed: Float,
    val verticalSpeed: Float,
    val rotationSpeed: Float,
    val verticalAmplitude: Float,
    val horizontalAmplitude: Float
)

@Composable
fun AnimatedBackground(
    modifier: Modifier = Modifier
) {
    val particles = remember {
        mutableStateListOf<LightParticle>().apply {
            addAll(List(40) {
                val x = Random.nextFloat() * 1000
                val y = Random.nextFloat() * 2000
                val isGolden = Random.nextFloat() > 0.3f
                val baseAlpha = Random.nextFloat() * 0.3f + 0.1f
                
                LightParticle(
                    initialX = x,
                    initialY = y,
                    currentX = x,
                    currentY = y,
                    radius = if (isGolden) Random.nextFloat() * 30 + 20 else Random.nextFloat() * 15 + 10,
                    speed = Random.nextFloat() * 0.4f + 0.1f,
                    angle = Random.nextFloat() * 2 * PI.toFloat(),
                    color = if (isGolden) {
                        Color(
                            red = 1f,
                            green = Random.nextFloat() * 0.2f + 0.8f,
                            blue = Random.nextFloat() * 0.1f,
                            alpha = baseAlpha
                        )
                    } else {
                        Color(
                            red = Random.nextFloat() * 0.2f + 0.8f,
                            green = Random.nextFloat() * 0.2f + 0.7f,
                            blue = Random.nextFloat() * 0.1f,
                            alpha = baseAlpha * 0.7f
                        )
                    },
                    baseAlpha = baseAlpha,
                    pulseSpeed = Random.nextFloat() * 2 + 1
                )
            })
        }
    }

    val bubbles = remember {
        mutableStateListOf<FloatingBubble>().apply {
            addAll(List(6) {
                FloatingBubble(
                    state = MutableBubbleState(
                        currentX = -Random.nextFloat() * 800,
                        currentY = Random.nextFloat() * 1000,
                        baseY = Random.nextFloat() * 1000
                    ),
                    size = 40f + Random.nextFloat() * 30,
                    horizontalSpeed = 1f + Random.nextFloat() * 0.5f,
                    verticalSpeed = 0.3f + Random.nextFloat() * 0.2f,
                    rotationSpeed = 15f + Random.nextFloat() * 10f,
                    verticalAmplitude = 50f + Random.nextFloat() * 30f,
                    horizontalAmplitude = 20f + Random.nextFloat() * 10f
                )
            })
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "background")
    val currentTime by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 15000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "time"
    )

    val glowScale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow"
    )

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            // Draw dark background with gradient
            drawRect(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF1A1A1A),
                        Color(0xFF000000)
                    ),
                    center = Offset(size.width / 2, size.height / 2),
                    radius = size.width
                ),
                size = size
            )

            // Draw glowing background elements
            drawBackgroundGlow(glowScale)

            // Draw floating bubbles with enhanced movement
            bubbles.forEach { bubble ->
                with(bubble.state) {
                    // Calculate horizontal movement with slight wave pattern
                    val baseX = currentX + bubble.horizontalSpeed * 2
                    val waveX = sin(horizontalPhase) * bubble.horizontalAmplitude
                    val newX = baseX + waveX
                    
                    // Calculate vertical floating movement
                    val verticalOffset = sin(currentTime * bubble.verticalSpeed + horizontalPhase) * bubble.verticalAmplitude
                    val newY = baseY + verticalOffset

                    // Update phase for continuous wave motion
                    horizontalPhase += 0.02f

                    // Reset bubble when it goes off screen
                    if (newX > size.width + 100) {
                        currentX = -100f - Random.nextFloat() * 200
                        baseY = Random.nextFloat() * size.height
                        scale = 0f
                        alpha = 0f
                    } else {
                        currentX = newX
                        currentY = newY
                        // Smooth scale and alpha transitions
                        scale = minOf(scale + 0.03f, 1f)
                        alpha = minOf(alpha + 0.03f, 1f)
                    }

                    // Update rotation with slight variation based on vertical movement
                    rotation += bubble.rotationSpeed * currentTime * (1f + sin(currentTime) * 0.2f)
                }

                // Draw bubble with enhanced visual effects
                withTransform({
                    rotate(bubble.state.rotation, Offset(bubble.state.currentX + bubble.size/2, bubble.state.currentY + bubble.size/2))
                    scale(bubble.state.scale, Offset(bubble.state.currentX + bubble.size/2, bubble.state.currentY + bubble.size/2))
                }) {
                    // Inner glow
                    drawRect(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFFFD700).copy(alpha = 0.25f * bubble.state.alpha),
                                Color(0xFFFFD700).copy(alpha = 0.15f * bubble.state.alpha),
                                Color.Transparent
                            ),
                            center = Offset(bubble.size/2, bubble.size/2),
                            radius = bubble.size * 0.8f
                        ),
                        topLeft = Offset(bubble.state.currentX, bubble.state.currentY),
                        size = Size(bubble.size, bubble.size)
                    )
                    
                    // Outer glow effect
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFFFD700).copy(alpha = 0.1f * bubble.state.alpha),
                                Color.Transparent
                            )
                        ),
                        radius = bubble.size * 1.2f,
                        center = Offset(
                            bubble.state.currentX + bubble.size/2,
                            bubble.state.currentY + bubble.size/2
                        )
                    )
                }
            }

            // Draw particles with enhanced effects
            particles.forEach { particle ->
                // Calculate new positions
                val newPosition = calculateParticlePosition(
                    initialX = particle.initialX,
                    initialY = particle.initialY,
                    speed = particle.speed,
                    angle = particle.angle,
                    time = currentTime
                )
                
                // Update particle state
                with(particle) {
                    currentX = newPosition.first
                    currentY = newPosition.second
                }

                // Calculate visual effects
                val pulsingAlpha = particle.baseAlpha * (0.7f + sin(currentTime * 4f * PI.toFloat() * particle.pulseSpeed) * 0.3f)
                val particleScale = 1f + sin(currentTime * 2f * PI.toFloat() * particle.pulseSpeed) * 0.2f

                // Draw particle
                drawParticle(
                    particle = particle,
                    scale = particleScale,
                    alpha = pulsingAlpha
                )
            }

            // Add atmospheric blur effect
            drawRect(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF000000).copy(alpha = 0f),
                        Color(0xFF000000).copy(alpha = 0.3f)
                    ),
                    center = Offset(size.width / 2, size.height / 2),
                    radius = size.width * 0.7f
                ),
                size = size
            )
        }
    }
}

private fun DrawScope.drawBackgroundGlow(scale: Float) {
    val centerX = size.width / 2
    val centerY = size.height / 2

    // Draw ambient glow
    withTransform({
        scale(scale, Offset(centerX, centerY))
    }) {
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFFFFD700).copy(alpha = 0.05f),
                    Color(0xFFFFD700).copy(alpha = 0.02f),
                    Color.Transparent
                )
            ),
            radius = size.minDimension * 0.4f,
            center = Offset(centerX, centerY)
        )
    }
}

private fun calculateParticlePosition(
    initialX: Float,
    initialY: Float,
    speed: Float,
    angle: Float,
    time: Float
): Pair<Float, Float> {
    val x = initialX + sin(time * 2f * PI.toFloat() * speed + angle) * 30f
    val y = initialY + cos(time * 2f * PI.toFloat() * speed + angle) * 30f
    return x to y
}

private fun DrawScope.drawParticle(
    particle: LightParticle,
    scale: Float,
    alpha: Float
) {
    withTransform({
        scale(scale, Offset(particle.currentX, particle.currentY))
    }) {
        // Draw main glow
        drawCircle(
            color = particle.color.copy(alpha = alpha),
            radius = particle.radius,
            center = Offset(particle.currentX, particle.currentY)
        )

        // Draw outer glow
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    particle.color.copy(alpha = alpha * 0.5f),
                    particle.color.copy(alpha = 0f)
                ),
                radius = particle.radius * 2
            ),
            radius = particle.radius * 2,
            center = Offset(particle.currentX, particle.currentY)
        )
    }
}