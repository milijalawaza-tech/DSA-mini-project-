STRUCTURE Node
    data
    next = NULL

STRUCTURE LinkedList
    head = NULL

INSERTING A NODE
START
insertNode(list, value)
    newNode = new Node(value)
    IF list.head == NULL THEN
        list.head = newNode
    ELSE
        current = list.head
        WHILE current.next != NULL DO
            current = current.next
        ENDWHILE
        current.next = newNode     
    ENDIF
END 


DELETING A NODE
START
 deleteNode(list, value)
    IF list.head == NULL THEN
        PRINT "Error: List is empty"
        RETURN
    ENDIF

    IF list.head.data == value THEN
        list.head = list.head.next  
        RETURN
    ENDIF

    previous = list.head
    current = list.head.next
    WHILE current != NULL DO
        IF current.data == value THEN
            previous.next = current.next
            RETURN
        ENDIF
        previous = current
        current = current.next
    ENDWHILE

    PRINT "Value not found"
END 


SEARCHING FOR A NODE
START
 searchNode(list, value)
    current = list.head
    position = 0
    WHILE current != NULL DO
        IF current.data == value THEN
            RETURN position          
        ENDIF
        current = current.next
        position = position + 1
    ENDWHILE
    RETURN -1                        
END 


TREVERSING A LIST
START
 traverseList(list)
    current = list.head
    WHILE current != NULL DO
        PRINT current.data
        current = current.next
    ENDWHILE
END 