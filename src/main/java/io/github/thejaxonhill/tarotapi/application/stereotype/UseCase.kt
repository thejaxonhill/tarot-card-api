package io.github.thejaxonhill.tarotapi.application.stereotype

import org.springframework.stereotype.Service

@Service
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class UseCase()