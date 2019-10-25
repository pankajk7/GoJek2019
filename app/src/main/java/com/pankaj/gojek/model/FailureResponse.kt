package com.pankaj.gojek.model

open class FailureResponse(var message: String? = null,
                           var code: Int? = 0,
                           var throwable: Throwable? = null,
                           var obj: Any? = null)