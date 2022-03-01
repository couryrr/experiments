class PointDetail{
    constructor(x, y, value){
        this.x = x
        this.y = y
        this.value = value
    }
}

class SquareDetail{
    constructor(p1, p2, p3, p4){
        this.p1 = p1
        this.p2 = p2
        this.p3 = p3
        this.p4 = p4
    }
    case = () => {
        return `${this.p1.value}${this.p2.value}${this.p3.value}${this.p4.value}`
    }
}

const debugging = false
const width = 850
const height = 850
const detail = 25
const backgroundColor = 150
let onColor = 255
let offColor = 0
let globalSquareMesh

function setup(){
    onColor = color(30,89,4)
    offColor = color(89,57,4)
    createCanvas(width,height)
    background(backgroundColor);
    
    const noiseMesh = generateNoiseMesh(width, height, detail)
    //drawNoiceMesh(noiseMesh)
    globalSquareMesh = generateSquareMesh(noiseMesh)

    //drawSquareMesh(globalSquareMesh)
    //frameRate(1)
    //noLoop()
}

function draw(){
    
    if(globalSquareMesh){
        drawSquareMesh(globalSquareMesh)
    }
    
    
}

const generateNoiseMesh = (width, height, detail) => {
    const noiseMesh = []
    for(let x = 0; x <= width/detail; x++){
        noiseMesh[x] = []
        for(let y = 0; y <= height/detail; y++){
            noiseMesh[x][y] = new PointDetail(x*detail, y*detail, Math.round(Math.random()))
        }
    }
    return noiseMesh
}

const drawNoiceMesh = (noiseMesh) => {
    strokeWeight(10)
    for(const row of noiseMesh){
        for(const noise of row){
            stroke(noise.value*onColor)
            point(noise.x, noise.y)
        }
    }
    
}

const generateSquareMesh = (noiseMesh) => {
    const squareMesh = []
    for(let x = 0; x < noiseMesh.length-1; x++){
        squareMesh[x] = []
        for(let y = 0; y < noiseMesh[x].length-1; y++){
            squareMesh[x][y] = new SquareDetail(noiseMesh[x][y], noiseMesh[x+1][y], noiseMesh[x][y+1], noiseMesh[x+1][y+1])
        }
    }
    return squareMesh
}

const generateTestSquareMesh = (binary) => {
    const squareMesh = []
    squareMesh[0] = []
    squareMesh[0][0] = new SquareDetail(
        new PointDetail(0,0,binary[0]),    //1
        new PointDetail(750,0,binary[1]),  //2
        new PointDetail(0,750,binary[2]),  //4
        new PointDetail(750,750,binary[3]) //8
    )
    
    return squareMesh
}

const drawSquareMesh = (squareMesh) => {
    clear()
    strokeWeight(0)
    for(const row of squareMesh){
        for(const square of row){
            const {p1, p2, p3, p4} = square    
            switch (square.case()) {
                case '0000': //0
                    if(debugging) console.log(square.case(), '0000')
                    stroke(offColor)
                    fill(offColor)
                    rect(p1.x, p2.y, detail)
                    
                    break;
                case '0001': //1
                    if(debugging) console.log(square.case(), '0001')

                    stroke(offColor)
                    fill(offColor)
                    rect(p1.x, p2.y, detail)

                    stroke(onColor)
                    fill(onColor)
                    triangle(p4.x, p4.y, p4.x, p4.y-detail/2, p4.x-detail/2, p4.y)
                
                    break;   
                case '0010': //2
                    if(debugging) console.log(square.case(), '0010')

                    stroke(offColor)
                    fill(offColor)
                    rect(p1.x, p2.y, detail)

                    stroke(onColor)
                    fill(onColor)
                    triangle(p3.x, p3.y, p3.x, p3.y-detail/2, p3.x+detail/2, p3.y)
                
                    break;
                case '0011'://3
                    if(debugging) console.log(square.case(), '0011')

                    stroke(offColor)
                    fill(offColor)
                    rect(p1.x, p2.y, detail, detail/2)
                    
                    stroke(onColor)
                    fill(onColor)
                    rect(p3.x, p4.y, detail, -1*detail/2)
                
                    break;
                case '0100': //4
                    if(debugging) console.log(square.case(), '0100')
                    stroke(offColor)
                    fill(offColor)
                    rect(p1.x, p2.y, detail)

                    stroke(onColor)
                    fill(onColor)
                    triangle(p2.x, p2.y, p2.x-detail/2, p2.y, p2.x, p2.y+detail/2)
                   
                    break;
                case '0101': //5
                    if(debugging) console.log(square.case(), '0101')

                    stroke(onColor)
                    fill(onColor)
                    rect(p1.x, p2.y, detail, detail)

                    stroke(offColor)
                    fill(offColor)
                    rect(p1.x, p2.y, detail/2, detail)

                    break;
                case '0110': //6
                    if(debugging) console.log(square.case(), '0110')

                    stroke(onColor)
                    fill(onColor)
                    rect(p1.x, p2.y, detail)

                    stroke(offColor)
                    fill(offColor)
                    triangle(p1.x, p1.y, p1.x+detail/2, p1.y, p1.x, p1.y+detail/2)
                    triangle(p4.x, p4.y, p4.x, p4.y-detail/2, p4.x-detail/2, p4.y)

                    break;
                case '0111'://7
                    if(debugging) console.log(square.case(), '0111')

                    stroke(onColor)
                    fill(onColor)
                    rect(p1.x, p2.y, detail)

                    stroke(offColor)
                    fill(offColor)
                    triangle(p1.x, p1.y, p1.x+detail/2, p1.y, p1.x, p1.y+detail/2)
                
                    break;
                case '1000': //8
                    if(debugging) console.log(square.case(), '1000')

                    stroke(offColor)
                    fill(offColor)
                    rect(p1.x, p2.y, detail)

                    stroke(onColor)
                    fill(onColor)
                    triangle(p1.x, p1.y, p1.x, p1.y+detail/2, p1.x+detail/2, p1.y)
                
                    break;
                case '1001': //9
                    if(debugging) console.log(square.case(), '1001')

                    stroke(offColor)
                    fill(offColor)
                    rect(p1.x, p2.y, detail)

                    stroke(onColor)
                    fill(onColor)
                    triangle(p1.x, p1.y, p1.x+detail/2, p1.y, p1.x, p1.y+detail/2)
                    triangle(p4.x, p4.y, p4.x, p4.y-detail/2, p4.x-detail/2, p4.y)
                    break;
                case '1010': //10
                    if(debugging) console.log(square.case(), '1010')

                    stroke(offColor)
                    fill(offColor)
                    rect(p1.x, p2.y, detail, detail)

                    stroke(onColor)
                    fill(onColor)
                    rect(p1.x, p2.y, detail/2, detail)
                    
                    break;
                case '1011': //11
                    if(debugging) console.log(square.case(), '1011')

                    stroke(onColor)
                    fill(onColor)
                    rect(p1.x, p2.y, detail)

                    stroke(offColor)
                    fill(offColor)
                    triangle(p2.x, p2.y, p2.x-detail/2, p2.y, p2.x, p2.y+detail/2)
                
                    break;
                case '1100': //12
                    if(debugging) console.log(square.case(), '1100')

                    stroke(offColor)
                    fill(offColor)
                    rect(p3.x, p4.y, detail, -1*detail/2)
                    
                    stroke(onColor)
                    fill(onColor)
                    rect(p1.x, p2.y, detail, detail/2)
                
                    break;
                case '1101': //13
                    if(debugging) console.log(square.case(), '1101')

                    stroke(onColor)
                    fill(onColor)
                    rect(p1.x, p2.y, detail)

                    stroke(offColor)
                    fill(offColor)
                    triangle(p3.x, p3.y, p3.x, p3.y-detail/2, p3.x+detail/2, p3.y)
                
                    break;
                case '1110': //14
                    if(debugging) console.log(square.case(), '1110')

                    stroke(onColor)
                    fill(onColor)
                    rect(p1.x, p2.y, detail)

                    stroke(offColor)
                    fill(offColor)
                    triangle(p4.x, p4.y, p4.x, p4.y-detail/2, p4.x-detail/2, p4.y)
                
                    break;
                case '1111': //15
                    if(debugging) console.log(square.case(), '1111')

                    stroke(onColor)
                    fill(onColor)
                    rect(p1.x, p2.y, detail)
                
                    break;
                default:
                    console.log(square.case(), 'Issue')
                    break;
            }
        }
    }
}

const handleUpdateClick = () => {
    clear()
    background(backgroundColor);
    const value = parseInt(document.getElementById('case').value)
    console.log(value)
    if(0 <= value && value <= 15){
        const binary = value.toString(2).padStart(4, '0')
        console.log("good value", binary)
        globalSquareMesh = generateTestSquareMesh(binary) //generateSquareMesh(noiseMesh)
        redraw()
    } else {
        console.log("bad value")
    }
}

const handleInvertClick = () => {
    const save = onColor
    onColor = offColor
    offColor = save
    redraw()
}

