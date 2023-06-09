import {AfterViewInit, Component, ElementRef, ViewChild} from '@angular/core';

@Component({
  selector: 'app-radial-slider',
  templateUrl: './radial-slider.component.html',
  styleUrls: ['./radial-slider.component.scss']
})
export class RadialSliderComponent implements AfterViewInit{
  @ViewChild('knob') knobDiv: ElementRef;
  drag = false;
  rotationValue = 0;
  startPos = { x: 0, y: 0 };
  windowRef: any;

  constructor(private window: Window) {
    this.windowRef = window;
  }

  ngAfterViewInit() {
    var self = this;
    this.knobDiv.nativeElement.addEventListener('mousedown', function(e: any) { self.dragStart(e);});
    this.knobDiv.nativeElement.addEventListener('touchstart', function(e: any) { self.dragStart(e);});
  }

  dragStart(e: any) {
    e.stopPropagation();
    if(e.cancelable) e.preventDefault();
    this.drag = true;
    this.startPos = this.getMousePos(e);

    var self = this;
    var funcMove = function(e: any) {
      e.stopPropagation();
      if(e.cancelable) e.preventDefault();

      self.calcRotation(e);
    }
    var removeListener = function(e: any) {
      e.stopPropagation();
      if(e.cancelable) e.preventDefault();
      self.windowRef.removeEventListener('touchmove', funcMove);
      self.windowRef.removeEventListener('mousemove', funcMove);
      self.windowRef.removeEventListener('touchend', removeListener);
      self.windowRef.removeEventListener('mouseup', removeListener);
    }

    this.windowRef.addEventListener('touchmove', funcMove, {passive: false});
    this.windowRef.addEventListener('mousemove', funcMove, {passive: false});
    this.windowRef.addEventListener('touchend', removeListener, {passive: false});
    this.windowRef.addEventListener('mouseup', removeListener, {passive: false});
  }

  getMousePos(e: any){
    var mousePos = { x: 0, y: 0};
    if(e.touches){
      mousePos.x = e.touches[0].clientX;
      mousePos.y = e.touches[0].clientY;
    } else {
      mousePos.x = e.clientX;
      mousePos.y = e.clientY;
    }
    return mousePos;
  }

  calcRotation(e: any){
    var curPos = this.getMousePos(e);
    let dy = -(curPos.y - this.startPos.y);
    let dx = curPos.x - this.startPos.x;
    this.startPos = curPos;
    this.rotationValue += (dx + dy);
  }
}


//TODO: implement limit and value conversation
