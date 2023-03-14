enum class Directions {
    North {
        override fun opposite() = South
        override fun right() = East
        override fun left() = West
    },
    South {
        override fun opposite() = North
        override fun right() = West
        override fun left() = East
    },
    East {
        override fun opposite() = West
        override fun right() = South
        override fun left() = North
    },
    West {
        override fun opposite() = East
        override fun right() = North
        override fun left() = South
    };
    
    abstract fun opposite(): Directions
    abstract fun right(): Directions
    abstract fun left(): Directions

}