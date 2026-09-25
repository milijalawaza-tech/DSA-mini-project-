SELECTION-SORT(array):
    n = length of array

    for i = 0 to n - 2:
        minIndex = i

        for j = i + 1 to n - 1:
            if array[j] < array[minIndex]:
                minIndex = j

        if minIndex != i:
            swap array[i] and array[minIndex]

OUTPUT:
    Sorted array