package com.company.resumewebapp.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class ExceptionLogAdvice {



    @ExceptionHandler(Exception.class)   /// Exception yazilir ki ne error olsa. Xususi olara MyException yarada bilersen ancaq onu vere bilersen bura
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleValidationException(final Exception ex){      // Burda geriye ResController yaratmaq istesen ResponseDTO qaytarmali idi

        return "error";
    }
}



// Eslinde bu class ancaq cliente seliqeli bir error sehifesi qaytarmaq ucun kecerlidir. CUnki sen Internal error qaytarsan musteri ne basa dusecekki bu nedir.
// Ona gore de bu yazdigimizi silirik---> private static final Logger LOG = Logger.getLogger(ExceptionLogAdvice.class.getName());  // bu o demekdir ki log bas verende name olaraq classin adinda ver
// hemcinin bunu da -->    LOG.log(Level.SEVERE, null, ex);

// Indi ozun ucun log yaratmaq isteyirsense hansi ki neye gore hansi deyer gedli ki bu error bas verdi bax onda Aspect ise dusur cunki orda JoinPoint var/
// ASpecte geriye veb sehife yuxaridaki kimi error page qaytarmaq olmadigi ucun ona gore ControllerAdvice ehtiyyac duyulur