INSERTION-SORT(array):
    n = length of array

    for i = 1 to n - 1:
        key = array[i]
        j = i - 1

        while j >= 0 and array[j] > key:
            array[j + 1] = array[j]
            j = j - 1

        array[j + 1] = key

OUTPUT:
    Sorted array