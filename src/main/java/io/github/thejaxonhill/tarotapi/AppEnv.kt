package io.github.thejaxonhill.tarotapi

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AppEnv(@Value(value = $$"${app.host}") val host: String)