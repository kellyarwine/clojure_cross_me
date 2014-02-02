(ns cross-me-spec
  (:require [speclj.core :refer :all]
            [cross-me    :refer :all]))

;it builds a grid
;it allows me to input clues
;it can make solid moves
;it can make x's??
;it can mark solid half lines
;it can mark 2 partials
;will it have an algorithm by then to play any move?

(describe "cross me"
  (context "builds a garmeboard"

    )

  (context "builds a grid"
    ; (it "builds a row"
    ;   (should= '("     \n") (build-row 5))))

    ; (it "builds a column"
    ;   (should= '(" " " " " " " " " " ) (build-column 5))))

  ; (it "builds grid lines"
  ;   (build-board))
  (it "builds a grid"
    (should= 0 (grid '(6 4 3 2 1) '(5 4 3 2 1 1))))

  ; (it "builds a grid"
  ;   (should= 0 (grid '(1 1 1 1 2) '(1 1 1 1 1 1))))

))