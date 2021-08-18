(ns starter.macros
  (:require [reagent.core :as r]))

(def with-let3 ^:sci/macro
  (fn [_ _ bindings & body]
    `(r/with-let ~bindings ~@body)))

(defmacro with-let4 [_ _ bindings & body]
  `(r/with-let ~bindings ~@body))
