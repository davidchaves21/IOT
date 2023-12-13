import React, { useState } from 'react'
import { Text } from 'react-native'

const Shoppingcart = ({route}:any) => {
    const {shoppingCart} = route.parame
    return (
        shoppingCart.map((prod: any, i: number) => (
            <Text>{prod.name}</Text>

        ))
  )
}

export default Shoppingcart
