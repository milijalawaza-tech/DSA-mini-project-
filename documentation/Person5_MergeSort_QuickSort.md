

MERGESORT  PSEUDOCODE
MERGE-SORT(array, left, right):
    if left < right:
        mid = (left + right) / 2
        MERGE-SORT(array, left, mid)
        MERGE-SORT(array, mid + 1, right)
        MERGE(array, left, mid, right)

MERGE(array, left, mid, right):
    n1 = mid - left + 1
    n2 = right - mid
    create arrays L[n1] and R[n2]
    copy array[left..mid] into L
    copy array[mid+1..right] into R
    i = 0, j = 0, k = left
    while i < n1 and j < n2:
        if L[i] <= R[j]:
            array[k] = L[i]
            i = i + 1
        else:
            array[k] = R[j]
            j = j + 1
        k = k + 1

OUTPUT:  
QUICK SORT PSEUDOCODE
QUICK-SORT(array, low, high):
    if low < high:
        pivotIndex = PARTITION(array, low, high)
        QUICK-SORT(array, low, pivotIndex - 1)
        QUICK-SORT(array, pivotIndex + 1, high)

PARTITION(array, low, high):
    pivot = array[high]        // last element as pivot
    i = low - 1
    for j = low to high - 1:
        if array[j] <= pivot:
            i = i + 1
            swap array[i] and array[j]
    swap array[i + 1] and array[high]
    return i + 1
OUTPUT:  
MERGE SORT DIAGRAM
              (top to bottom)
  [17, 5, 23, 8, 14, 3, 11, 20, 6, 9]
                         /                    \
              [17, 5, 23, 8, 14]           [3, 11, 20, 6, 9]
               /            \                /            \
        [17, 5]        [23, 8, 14]       [3, 11]       [20, 6, 9]
         /   \          /    \            /   \         /    \
     [17]  [5]      [23]  [8, 14]      [3]  [11]     [20]  [6, 9]
                            /  \                              /  \
                         [8]  [14]                         [6]  [9]
(bottom to top, sorted)
     [17]  [5]      [23]  [8]  [14]      [3]  [11]     [20]  [6]  [9]
         \  /            \   /  \            \  /          \   /  \
        [5, 17]      [8, 14]  [23]        [3, 11]     [6, 9]  [20]
              \        /                              \        /
            [5, 8, 14, 17, 23]                    [3, 6, 9, 11, 20]
                       \                              /
                [3, 5, 6, 8, 9, 11, 14, 17, 20, 23]

3. QUICK SORT DIAGRAM 
Original array: [17, 5, 23, 8, 14, 3, 11, 20, 6, 9]
Stage 1:
•	Pivot = 9 (last element)
•	Partition all elements ≤ 9 to the left, > 9 to the right.
•	Left partition: [5, 8, 3, 6]
•	Right partition: [17, 23, 14, 11, 20]
•	9 moves to its final position.
•	Array after Stage 1: [5, 8, 3, 6, 9, 17, 23, 14, 11, 20]
Stage 2a (left side):
•	Sub-array: [5, 8, 3, 6], Pivot = 6
•	Left partition: [5, 3]
•	Right partition: [8]
•	Array: [5, 3, 6, 8, 9, ...]
Stage 2b (right side):
•	Sub-array: [17, 23, 14, 11, 20], Pivot = 20
•	Left partition: [17, 14, 11]
•	Right partition: [23]
•	Array: [..., 17, 14, 11, 20, 23]
Final sorted result: [3, 5, 6, 8, 9, 11, 14, 17, 20, 23


    




