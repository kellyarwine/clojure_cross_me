(ns cross-me)

(def new-line "\n")

(defn build-row-border [value]
  (str value "|"))

(defn build-row-contents [value]
  (take value (repeat "x")))

(defn build-row [value]
  (concat
    (build-row-border 5)
    (build-row-contents 5)
    new-line))

(defn build-board []
  (apply println
    (concat
       '("\n")
       (build-row 5)
       (build-row 5)
       (build-row 5)
       (build-row 5)
       (build-row 5)
       '("  +----------\n"))))

(defn mark-cell? [amt-marked index size]
    (and (> (inc index) (- size amt-marked))
         (< index amt-marked)))

(defn get-row-contents [row-clues column-clues row-index]
  (concat
    (for [column-index (range (count column-clues))]
        (if (or (mark-cell? (nth row-clues row-index) column-index (count column-clues))
                (mark-cell? (nth column-clues column-index) row-index (count row-clues)))
          "x "
          "- "))))

(defn grid [row-clues column-clues]
  (apply println
    (concat
      (for [row-index (range (count row-clues))]
        (apply str
          "\n"
          (cons (str (nth row-clues row-index) " ")
          (get-row-contents row-clues column-clues row-index))))
      "\n"
      column-clues)
  ))

; 1|■   ■
; 2|  ■ ■
; 1|    ■
; 1|    ■ ■
; 2|    ■   ■ ■
;  +-------------
;   1 1 5 1 1 1

; 5|■ ■ ■ ■ ■
; 3|  ■ ■ ■
; 1|    ■
; 3|  ■ ■ ■
; 5|■ ■ ■ ■ ■
;  +---------
;   1 2 5 2 1
   ;1 2   2 1

; 6|■ ■ ■ ■ ■ ■
; 4|■ ■ ■ ■
; 3|■ ■ ■
; 2|■ ■
; 1|■
;  +-------------
;   5 4 3 2 1 1

;if number in column var = count of rows then you know whole row is shaded
; vice versa

;if number in column var > 1/2 of count of rows, some can be shaded...
;so if clue is 4 and row count is 5, then 3 can be shaded...
;(5-4) = 1, then 1 cannot be shaded and 5 cannot be shaded
;if 5-3 = 2, then 1,2 cannot be shaded and 5,4 cannot be shaded
;shade starting at calc+1 and go to count-calc
; vice versa