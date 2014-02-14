(ns cross-me
  (:require [clojure.math.combinatorics :as comb]))

(def blank ".")
(defn blank? [value] (= blank value))
(defn not-blank? [value] (not= blank value))

(defn first-ok? [row-possibility]
  (or
    (and (not-blank? (first row-possibility))
         (= 1 (count row-possibility)))
    (and (not-blank? (first row-possibility))
         (blank? (second row-possibility)))
    (blank? (first row-possibility))))

(defn last-ok? [row-possibility]
  (or
    (and (not-blank? (last row-possibility))
         (= 1 (count row-possibility)))
    (and (not-blank? (last row-possibility))
         (blank? (nth row-possibility (- (count row-possibility) 2))))
    (blank? (last row-possibility))))

(defn middles-ok? [row-possibility]
  (loop [row-possibility row-possibility
         i 1]
    (if (or (= 1 (count row-possibility))
            (= i (dec (count row-possibility))))
      true
      (if (= false (or
                     (blank? (nth row-possibility i))
                     (and (blank? (nth row-possibility (inc i)))
                          (blank? (nth row-possibility (dec i))))))
        false
        (recur row-possibility (inc i))))))

(defn blanks-between? [row-possibility]
  (and (first-ok? row-possibility)
       (last-ok? row-possibility)
       (middles-ok? row-possibility)))

(defn in-right-sequence? [row-possibility segments]
  (= segments (filter not-blank? row-possibility)))

(defn flatten-nested-vectors [vector-with-nests]
  (map #(flatten %) vector-with-nests))

(defn row-possibilities [row row-clue]
  (let [width (count row)
        filled-spaces (reduce + row-clue)
        segments (into [] (map #(into [] (repeat % "x")) row-clue))
        empty-spaces (into [] (repeat (- width filled-spaces) blank))]
    (flatten-nested-vectors
      (into []
      (filter #(and (blanks-between? %) (in-right-sequence? % segments))
        (into [] (comb/permutations
          (concat segments empty-spaces))))))))

