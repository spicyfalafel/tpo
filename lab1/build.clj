(ns build
  (:require [clojure.tools.build.api :as b]
            ; [clojure.string :as str]
            [clojure.java.io :as io])
  (:import (java.io File)))

(set! *warn-on-reflection* true)

;; HELPERS
; (defn- mkdirs [x] (.mkdirs (File. x)))

(defn keywordize-map [mp]
  (-> (apply hash-map mp)
      (update-keys keyword)))

(defn delete-recursively [^java.io.File f]
  (when (.isDirectory f)
    (doseq [c (.listFiles f)]
      (delete-recursively c)))
  (io/delete-file f))


(defn clean []
  (b/delete {:path "target"}))

(def build-basis (b/create-basis {:project "deps.edn"}))
(def class-dir "target/classes")
(def clj-main 'hello)
(def java-main-class 'mypackage/MyClass)
(def version "1.1.1")

(defn compile-java []
  (println ::compile-java)
  (b/javac {:src-dirs ["java"]
            :class-dir class-dir
            :basis build-basis
            :javac-opts ["-source" "8" "-target" "8"]})
  (b/write-pom {:class-dir class-dir
                :lib java-main-class
                :version version
                :basis build-basis
                :src-dirs ["src"]})
  (println ::compile-java-end))

(defn build-jar []
  (println ::build-jar)
  (b/compile-clj
    {:basis build-basis
     :src-dirs ["src"]
     :class-dir class-dir})

  (b/uber
    {:class-dir class-dir
     :basis build-basis
     ; :exclude ["LICENSE"]
     :uber-file "target/myproj.jar"
     :main java-main-class})
  (println ::build-jar-end))

(defn uber [_]
  (clean)
  (compile-java)
  (build-jar))


