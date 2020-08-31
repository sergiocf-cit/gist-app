package com.sergio.gistapp.gist.util

import org.mockito.ArgumentCaptor
import org.mockito.Mockito

class MockitoHelper {
    companion object {
        fun <Type> capture(argumentCaptor: ArgumentCaptor<Type>): Type = argumentCaptor.capture()
        fun <Type> any(type: Class<Type>): Type = Mockito.any(type)
    }
}
