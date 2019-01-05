package com.example.sergio.libraryii

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        *  Se importa desde File -> New -> Import Module y se selecciona el modulo de la librería creada.
        *  Se hace una copia en el proyecto de myLibrary. Para actualizarla se tiene que borrar y volver a
        *  importar si se hace de este modo.
        *
        *  Si se exporta como un archivo AAR (Android Archive), no se puede editar el código y se tiene que borrar e importar
        *  al actualizarse. Para generar el fichero AAR:
        *  - Mejor crear la librería sin ningún otro código que la esté utilizando (como en el caso de MyLibrary). Habría que borrar
        *  la app que la está usando en ese momento.
        *  - Coger archivo AAR, cambiar la vista a Project e ir a: mylibrary -> build -> outputs -> aar y coger el archivo que
        *  tiene esa carpeta. (Si no estuviera, hacer un Rebuild Project)
        *  - En la app donde se quiera importar, File -> New -> New Module. Se usa la opcion importa el .JAR/AAR y se
        *  selecciona el fichero AAR.
        *
        * Para importarlo de forma que todos puedan usarla y modificarla, actualizandose en tiempo real:
        * - Ir al Settings.gradle e incluir:
        *                   include ':app', ':mylibrary'
        *                   project(':mylibrary').projectDir = new File('../My Library/mylibrary')
        *
        * - Despues ir al app Gradle, en las dependencies, y añadir:  compile project(':mylibrary')
        * Sincronizar despues el Gradle.
        *
        * */
    }
}
