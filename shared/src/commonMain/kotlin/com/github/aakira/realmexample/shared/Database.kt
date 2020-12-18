package com.github.aakira.realmexample.shared

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.runtimeapi.RealmModel
import io.realm.runtimeapi.RealmModule
import io.realm.runtimeapi.RealmObject

@RealmObject
class Person : RealmModel {
    var name: String = "hoge"
    var age: Int = 46
}

@RealmModule
class Entities

object Database {

    private val realm: Realm by lazy {
        val configuration = RealmConfiguration.Builder()
            .schema(Entities())
            .build()

        Realm.open(configuration)
    }

    fun addPerson(name: String, age: Int): Person {
        realm.beginTransaction()
        val person = realm.create(Person::class).apply {
            this.name = name
            this.age = age
        }
        realm.commitTransaction()
        return person
    }

    fun persons(): List<Person> {
        return realm.objects(Person::class)
    }

    fun queryPerson(name: String): List<Person> {
        return realm.objects(Person::class).query("name = $0", name)
    }

    fun deletePersons() {
        realm.beginTransaction()
        realm.objects(Person::class).delete()
        realm.commitTransaction()
    }
}
