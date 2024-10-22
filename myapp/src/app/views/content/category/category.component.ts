import { CategoriesService } from './../../../shared/services/categories.service';

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss'],
})
export class CategoryComponent implements OnInit {
  constructor(private categoryService: CategoriesService) {}
  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.categoryService.getAll().subscribe((result) => {
      debugger;
      console.log(result);
    });
  }
}
