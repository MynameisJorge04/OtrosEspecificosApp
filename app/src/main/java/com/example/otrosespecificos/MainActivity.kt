import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.otrosespecificos.R

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var orientationSensor: Sensor
    private lateinit var textViewOrientation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        orientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION)

        textViewOrientation = findViewById(R.id.textViewOrientation) // Asegúrate de que este ID coincida con tu diseño
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, orientationSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ORIENTATION) {
            val azimuth = event.values[0]
            val pitch = event.values[1]
            val roll = event.values[2]

            // Actualiza los TextViews u otros elementos de la interfaz de usuario
            textViewOrientation.text = "Azimuth: $azimuth\nPitch: $pitch\nRoll: $roll"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No es necesario implementar esto en este ejemplo
    }
}
