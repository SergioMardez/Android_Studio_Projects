/*
 * Copyright (c) 2017 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.cheesefinder

import android.text.Editable
import android.text.TextWatcher
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_cheeses.*
import java.util.concurrent.TimeUnit

class CheeseActivity : BaseSearchActivity() {

    //Añadir la interface disposable que termina una suscripcion a un observable
    private lateinit var disposable: Disposable

    override fun onStart() {
        super.onStart()

        //Para hacer el observable cuando cambia el texto y cuando se pulse al boton. Se utiliza merge
        // para juntar items de mas de un observable en un unico observable
        val buttonClickStream = createButtonClickObservable()
        val textChangeStream = createTextChangeObservable()

        val searchTextObservable = Observable.merge<String>(buttonClickStream, textChangeStream)



        // 1 Se crea un observable llamando al metodo observable
        //val searchTextObservable = createButtonClickObservable()

        /*searchTextObservable
                // 2 Se suscribe al observable y se le pasa un consumer simple
                .subscribe { query ->
                    // 3 Se realiza la busqueda y se muestran los resultados
                    showResult(cheeseSearchEngine.search(query))
                }*/

        disposable = searchTextObservable
                // 1 Se especifica que empiece en el hilo principal en vez de en el de entrada/salida
                // All codigo que trabaja con la vista se debe ejecutar en el main.
                .subscribeOn(AndroidSchedulers.mainThread())
                // Para asegurar que se ejecutara en el hilo principal y se llame cuando un nuevo item es emitido
                //.doOnNext {showProgress()}
                // 2 Especifica que el siguiente operador debe llamarse en el hilo I/O
                .observeOn(Schedulers.io())
                // 3 Para cada query de busqueda, se devuelven los resultados
                .map { cheeseSearchEngine.search(it) }
                // 4 Se pasan los resultados al hilo principal
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    // Se oculta el progreso cuando está a punto de ser mostrado
                    hideProgress()
                    showResult(it)
                }
    }

    // 1 Funcion que va a devolver un observable para los cambios de texto
    private fun createTextChangeObservable(): Observable<String> {
        // 2 Se crea el observable, que coge un observable al que suscribirse
        val textChangeObservable = Observable.create<String> { emitter ->
            // 3 Cuando un observable se suscribe, se crea un textWatcher
            val textWatcher = object : TextWatcher {

                override fun afterTextChanged(s: Editable?) = Unit

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

                // 4 No hace falta fijarse en como es antes y o despues de editarse. Solo cuando el texto cambia
                override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    s?.toString()?.let { emitter.onNext(it) }
                }

            }

            // 5 Se añade el watcher al textView de esta manera
            queryEditText.addTextChangedListener(textWatcher)

            // 6 No hay que olvidarse de quitar el watcher
            emitter.setCancellable {
                queryEditText.removeTextChangedListener(textWatcher)
            }
        }

        // 7 Se debe devolver el observable creado. Añadido filtro para que al menos sean dos letras para realizar la busqueda
        return textChangeObservable.filter { it.length >= 2 }
                // debounce hace que tenga que pasar un cierto tiempo sin emisiones de items antes de que se ejecute el observable
                .debounce(1000, TimeUnit.MILLISECONDS)
    }



    // 1 Funcion que es observable que devuelve strings
    private fun createButtonClickObservable(): Observable<String> {
        // 2 Se crea con observable create
        return Observable.create { emitter ->
            // 3 El evento click en el boton de busqueda
            searchButton.setOnClickListener {
                // 4 Se llama a la funcion onNext, se le pasa el siguiente valor del query
                emitter.onNext(queryEditText.text.toString())
            }

            // 5 Para quitar las referencias y hacer que el observable deje de actuar cuando no hay observers
            // o cuando se ha completado su tarea
            emitter.setCancellable {
                // 6 La forma de quitar el listener del onClick es pasandolo a null
                searchButton.setOnClickListener(null)
            }
        }
    }

    //Para terminar la suscripcion al observable el mejor momento onStop
    @Override
    override fun onStop() {
        super.onStop()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }


}