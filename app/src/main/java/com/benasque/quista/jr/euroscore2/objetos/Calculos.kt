package com.benasque.quista.jr.euroscore2.objetos

import kotlin.math.exp

internal class Calculos
(
//<editor-fold desc="Variables localesConstantes">
        private val _Edad: String,
        private val _Sexo: Int,
        private val _CCS4: Boolean, //Clase funcional
        private val _NYHA: Int, //boolean _NYHA2; //boolean _NYHA3;  //boolean _NYHA4;
        private val _DM: Boolean,
        private val _Arteriopatia: Boolean,
        private val _epoc: Boolean,
        private val _movilidad: Boolean, //renal
        private val _Renal: Int, //boolean _CC5085; //boolean _CC50; //boolean _dialisis;
        private val _Endocarditis: Boolean,
        private val _Cirugia_Previa: Boolean,
        private val _Critico: Boolean, //Función ventricular
        private val _FEVI: Int, //boolean _FEVI3150; //boolean _FEVI2130; //boolean _FEVI20;
        private val _IAM_Reciente: Boolean, // Presión Pulmonar
        private val _HTP: Int, //boolean _HTP3155; //boolean _HTP55; // Urgencia
        private val _Urgencia: Int, //boolean _Urgente; //boolean _Emergencia; //boolean _Salvage; //Tipo cirugia
        private val _Tipo: Int, //boolean _no_CABG; //boolean _C2_procedimientos; //boolean _C3_procedimientos;
        private val _Cirugia_Toracica: Boolean) {
    //<editor-fold desc="Funciones Privadas">

    private fun miEdad(): Double {
        val temp: Int =if(_Edad == String()){
                           0
                        }else{
                           if (_Edad.toInt() < 61) {
                                1
                         } else {
                             _Edad.toInt() - 59
                         }
                    }
        return temp * c_Edad
    }

    private fun miSexo(): Double {
        return if (_Sexo == 1) c_Sexo  else 0.0
    }

    private fun miCCS4(): Double {
        return if (_CCS4) c_CCS4 else 0.0
    }

    private fun miNYHA(): Double {
        var temp = 0.0
        when (_NYHA) {
            0 -> temp = 0.0
            1 -> temp = c_NYHA2
            2 -> temp = c_NYHA3
            3 -> temp = c_NYHA4
        }
        return temp
    }

    private fun miDM(): Double {
        return if (_DM)  c_DM  else 0.0
    }

    private fun miArteriopatia(): Double {
        return if (_Arteriopatia) c_Arteriopatia  else 0.0
    }

    private fun miEpoc(): Double {
        return if (_epoc) c_epoc else 0.0

    }

    private fun miMovilidad(): Double {
        return if (_movilidad) c_movilidad else 0.0
    }

    private fun miRenal(): Double {
        var temp = 0.0
        when (_Renal) {
            0 -> temp = 0.0
            1 -> temp = c_CC5085
            2 -> temp = c_CC50
            3 -> temp = c_dialisis
        }
        return temp
    }

    private fun miEndocarditis(): Double {
        return if (_Endocarditis) c_Endocarditis else 0.0
    }

    private fun miCirugiaPrevia(): Double {
        return if (_Cirugia_Previa) c_Cirugia_Previa else 0.0
    }

    private fun miCritico(): Double {
        return if (_Critico) c_Critico else 0.0
    }

    private fun miFEVI(): Double {
        var temp = 0.0
        when (_FEVI) {
            0 -> temp = 0.0
            1 -> temp = c_FEVI3150
            2 -> temp = c_FEVI2130
            3 -> temp = c_FEVI20
        }
        return temp
    }

    private fun miIAMReciente(): Double {
        return if (_IAM_Reciente) c_IAM_Reciente else 0.0
    }

    private fun miHTP(): Double {
        var temp = 0.0
        when (_HTP) {
            0 -> temp = 0.0
            1 -> temp = c_HTP3155
            2 -> temp = c_HTP55
        }
        return temp
    }

    private fun miUrgencia(): Double {
        var temp = 0.0
        when (_Urgencia) {
            0 -> temp = 0.0
            1 -> temp = c_Urgente
            2 -> temp = c_Emergencia
            3 -> temp = c_Salvage
        }
        return temp
    }

    private fun miTipo(): Double {
        var temp = 0.0
        when (_Tipo) {
            0 -> temp = 0.0
            1 -> temp = c_no_CABG
            2 -> temp = c_C2_procedimientos
            3 -> temp = c_C3_procedimientos
        }
        return temp
    }

    private fun miCirugiaToracica(): Double {
        return if (_Cirugia_Toracica) c_Cirugia_Toracica else 0.0
    }

    //</editor-fold>
    fun resultado(): Double {
        val tempo: Double
        val temp: Double = miEdad() + miSexo() + miCCS4() + miNYHA() + miDM() + miArteriopatia() +
                miEpoc() + miMovilidad() + miRenal() + miEndocarditis() + miCirugiaPrevia() +
                miCritico() + miFEVI() + miIAMReciente() + miHTP() + miUrgencia() + miTipo() + miCirugiaToracica() + c_Constante
        tempo = exp(temp) / (1 + exp(temp))
        return tempo
    }

    companion object {
        //<editor-fold desc="Constantes">
        private const val c_Constante = -5.324537
        private const val c_Edad = 0.0285181
        private const val c_Sexo = 0.2196434
        private const val c_CCS4 = 0.2226147

        //Clase funcional
        private const val c_NYHA2 = 0.1070545
        private const val c_NYHA3 = 0.2958358
        private const val c_NYHA4 = 0.5597929
        private const val c_DM = 0.3542749
        private const val c_Arteriopatia = 0.5360268
        private const val c_epoc = 0.1886564
        private const val c_movilidad = 0.2407181

        //renal
        private const val c_CC5085 = 0.303553
        private const val c_CC50 = 0.8592256
        private const val c_dialisis = 0.6421508
        private const val c_Endocarditis = 0.6194522
        private const val c_Cirugia_Previa = 1.118599
        private const val c_Critico = 1.086517

        //Función ventricular
        private const val c_FEVI3150 = 0.3150652
        private const val c_FEVI2130 = 0.8084096
        private const val c_FEVI20 = 0.9346919
        private const val c_IAM_Reciente = 0.1528943

        // Presión Pulmonar
        private const val c_HTP3155 = 0.1788899
        private const val c_HTP55 = 0.3491475

        // Urgencia
        private const val c_Urgente = 0.3174673
        private const val c_Emergencia = 0.7039121
        private const val c_Salvage = 1.362947

        //Tipo cirugia
        private const val c_no_CABG = 0.0062118
        private const val c_C2_procedimientos = 0.5521478
        private const val c_C3_procedimientos = 0.9724533
        private const val c_Cirugia_Toracica = 0.6527205
    }
}