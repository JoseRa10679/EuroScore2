package com.benasque.quista.jr.euroscore2.ui

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.benasque.quista.jr.euroscore2.R
import com.benasque.quista.jr.euroscore2.databinding.ActivityMainBinding
import com.benasque.quista.jr.euroscore2.models.MainViewModel
import com.benasque.quista.jr.euroscore2.objetos.CFecha
import com.benasque.quista.jr.euroscore2.objetos.Calculos
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val miViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }


    //<editor-fold desc="Menu">
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu items for use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar items
        if (item.itemId == R.id.action_settings) {
            Intent(this, AcercaActivity::class.java).also {
                startActivity(it)
            }

        } else if (item.itemId == R.id.version) {
            var packageinfo: PackageInfo? = null
            try {
                packageinfo = packageManager.getPackageInfo(packageName, 0)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            var version: String? = null
            if (packageinfo != null) {
                version = packageinfo.versionName
            }
            Toast.makeText(this@MainActivity, "EuroScore 2 versión: $version", Toast.LENGTH_SHORT).apply {
                setGravity(Gravity.CENTER, 0, 0)
                show()
            }

        }
        return false
    }

    //</editor-fold>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBarId)


        // Comprueba fecha límite

        with(CFecha) {
            if (comprueba("01/01/2023")) alertaMSG(this@MainActivity)
        }

        miViewModel.getresultado().observe(this@MainActivity) {
            binding.txtResultado.text = it ?: "0"
        }

        binding.editTextEdad.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (binding.editTextEdad.text.toString() != String()) {
                    calcular()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        binding.txtResultado.setOnLongClickListener {
            limpiaControles()
            false
        }

        //<editor-fold desc="Cambios Spinner">


        binding.spSexo.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calcular()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                muestraTeclado(binding.editTextEdad)
            }
        }

        binding.spFuncionR.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calcular()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spNYHA.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calcular()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spFEVI.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calcular()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spHTP.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calcular()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spUrgencia.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calcular()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spUrgencia.setOnLongClickListener {

            Toast.makeText(applicationContext, R.string.toast_urgencia, Toast.LENGTH_LONG).apply {
                setGravity(Gravity.BOTTOM + Gravity.FILL_HORIZONTAL, 0, 0)
                show()
            }


            false
        }

        binding.spRelevancia.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calcular()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spRelevancia.setOnLongClickListener {
            Toast.makeText(applicationContext, R.string.toast_relevancia, Toast.LENGTH_LONG).apply {
                setGravity(Gravity.BOTTOM + Gravity.FILL_HORIZONTAL, 0, 0)
                show()
            }



            false
        }

        //</editor-fold>


        //<editor-fold desc="Cambios en CHK">

        binding.chkArteriopatia.setOnCheckedChangeListener { _, _ ->
            ocultaTeclado()
            calcular()
        }

        binding.chkArteriopatia.setOnLongClickListener {
            Toast.makeText(applicationContext, R.string.toast_arteriopatia, Toast.LENGTH_LONG).apply {
                setGravity(Gravity.BOTTOM + Gravity.FILL_HORIZONTAL, 0, 0)
                show()
            }

            false
        }

        binding.chkMovilidad.setOnCheckedChangeListener { _, _ ->
            ocultaTeclado()
            calcular()
        }

        binding.chkMovilidad.setOnLongClickListener {
            Toast.makeText(applicationContext, R.string.toast_movilidad_disminuida, Toast.LENGTH_LONG).apply {
                setGravity(Gravity.BOTTOM + Gravity.FILL_HORIZONTAL, 0, 0)
                show()
            }

            false
        }

        binding.chkCirugiaPrevia.setOnCheckedChangeListener { _, _ ->
            ocultaTeclado()
            calcular()
        }

        binding.chkEpoc.setOnCheckedChangeListener { _, _ ->
            ocultaTeclado()
            calcular()
        }

        binding.chkEndocarditis.setOnCheckedChangeListener { _, _ ->
            ocultaTeclado()
            calcular()
        }

        binding.chkCritico.setOnCheckedChangeListener { _, _ ->
            ocultaTeclado()
            calcular()
        }

        binding.chkCritico.setOnLongClickListener {
            Toast.makeText(applicationContext, R.string.toast_critico, Toast.LENGTH_LONG).apply {
                setGravity(Gravity.BOTTOM + Gravity.FILL_HORIZONTAL, 0, 0)
                show()
            }

            false
        }

        binding.chkDiabetes.setOnCheckedChangeListener { _, _ ->
            ocultaTeclado()
            calcular()
        }

        binding.chkCCS.setOnCheckedChangeListener { _, _ ->
            ocultaTeclado()
            calcular()
        }

        binding.chkIAM.setOnCheckedChangeListener { _, _ ->
            ocultaTeclado()
            calcular()
        }

        binding.chkIAM.setOnLongClickListener {
            Toast.makeText(applicationContext, R.string.toast_iam_reciente, Toast.LENGTH_LONG).apply {
                setGravity(Gravity.BOTTOM + Gravity.FILL_HORIZONTAL, 0, 0)
                show()
            }

            false
        }

        binding.chkAortica.setOnCheckedChangeListener { _, _ ->
            ocultaTeclado()
            calcular()
        }
        //</editor-fold>

        binding.rootLayout.setOnClickListener{
            ocultaTeclado()
        }


    }

    override fun onResume() {
        super.onResume()
        binding.editTextEdad.apply {
            requestFocus()
//            muestraTeclado(this)
        }
    }

    private fun calcular() {
        val temporal = Calculos(
                binding.editTextEdad.text.toString(),
                binding.spSexo.selectedItemPosition,
                binding.chkCCS.isChecked,
                binding.spNYHA.selectedItemPosition,
                binding.chkDiabetes.isChecked,
                binding.chkArteriopatia.isChecked,
                binding.chkEpoc.isChecked,
                binding.chkMovilidad.isChecked,
                binding.spFuncionR.selectedItemPosition,
                binding.chkEndocarditis.isChecked,
                binding.chkCirugiaPrevia.isChecked,
                binding.chkCritico.isChecked,
                binding.spFEVI.selectedItemPosition,
                binding.chkIAM.isChecked,
                binding.spHTP.selectedItemPosition,
                binding.spUrgencia.selectedItemPosition,
                binding.spRelevancia.selectedItemPosition,
                binding.chkAortica.isChecked
        ).resultado()

        val riesgoBajo = getString(R.string.strBajo)
        val riesgoModerado = getString(R.string.strModerado)
        val riesgoAlto = getString(R.string.strAlto)
        val formateador = DecimalFormat("##.##%")


        miViewModel.setresultado(
                when {
                    temporal < 0.02 -> String.format("%s%s", formateador.format(temporal), riesgoBajo)
                    temporal >= 0.02 && temporal < 0.05 -> String.format("%s%s", formateador.format(temporal), riesgoModerado)
                    else -> String.format("%s%s", formateador.format(temporal), riesgoAlto)
                }

        )
        
    }

    private fun limpiaControles() {

        binding.run {
            editTextEdad.setText("")
            chkArteriopatia.isChecked = false
            chkMovilidad.isChecked = false
            chkArteriopatia.isChecked = false
            chkMovilidad.isChecked = false
            chkCirugiaPrevia.isChecked = false
            chkEpoc.isChecked = false
            chkEndocarditis.isChecked = false
            chkCritico.isChecked = false
            chkDiabetes.isChecked = false
            chkCCS.isChecked = false
            chkIAM.isChecked = false
            chkAortica.isChecked = false
            spSexo.setSelection(0)
            spFuncionR.setSelection(0)
            spNYHA.setSelection(0)
            spFEVI.setSelection(0)
            spHTP.setSelection(0)
            spUrgencia.setSelection(0)
            spRelevancia.setSelection(0)

            editTextEdad.requestFocus()
            muestraTeclado(editTextEdad)

        }


    }

    // Oculta el teclado al activar el control
    private fun ocultaTeclado() {
        UIUtil.hideKeyboard(this@MainActivity)
    }

    // Muestra el teclado al activar el control
    private fun muestraTeclado(v: EditText?) {
        UIUtil.showKeyboard(this, v)
    }

}




