package com.example.nikolaiturev.weather.exstension

//import io.reactivex.Single
//import retrofit2.Call
//
//
//fun <T> Call<T>.singleResponse(): Single<T> = Single.create { emitter ->
//    this.response(
//        onSuccess = { code, item ->
//            if(!emitter.isDisposed) {
//                emitter.onSuccess(item)
//            }
//        },
//        onError = { code, message ->
//            if(!emitter.isDisposed) {
//                emitter.onError(Exception(message))
//            }
//        })
//}
//
//fun <T> Call<T>.singleResponseCode(): Single<Int> = Single.create { emitter ->
//    this.response(
//        onSuccess = { code, item ->
//            if (!emitter.isDisposed) {
//                emitter.onSuccess(code)
//            }
//        },
//        onSuccessEmpty = {
//            if (!emitter.isDisposed) {
//                emitter.onSuccess(it)
//            }
//        },
//        onError = { code, message ->
//            if (!emitter.isDisposed) {
//                emitter.onError(Exception(message))
//            }
//        })
//}


