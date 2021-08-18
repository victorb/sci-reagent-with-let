(ns starter.sci
  (:require
    [sci.core :as sci]
    [reagent.core :as r :refer [with-let]]
    [clojure.pprint :refer [pprint]]))

(def my-with-let
  ^:sci/macro
  (fn [_&form _&env bindings & body]
    `(with-let ~bindings ~@body)))

(def context
  (sci/init
    {:namespaces {'user {'ratom r/atom}
                         ;; 'with-let my-with-let}
                  'reagent.core {'with-let with-let}}}))

(defn eval-string [source]
  (try
   {:source source
    :result (sci/eval-string* context source)}
   (catch js/Error e
     {:source source
      :error (str e)})))
